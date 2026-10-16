package com.project.grimoirelavendel.dictionary.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.grimoirelavendel.dictionary.viewModel.DictionaryUIState
import com.project.grimoirelavendel.dictionary.viewModel.DictionaryViewModel

@Composable
fun DictionaryScreen (
    viewModel: DictionaryViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    Column (modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = state.selectedSearchResult == null,
            enter = expandVertically(animationSpec = tween(durationMillis = 300)),
            exit = shrinkVertically(animationSpec = tween(durationMillis = 500))
        ) {
            SearchBar (
                searchValue = state.searchQuery,
                searchInputChange = { viewModel.updateSearchValue(it) },
                clearSearch = { viewModel.clearSearch() }
            )
        }

        ResultsDisplay (
            viewModel = viewModel,
            state = state,
            listState = listState
        )
    }
}

@Composable
fun ResultsDisplay (
    viewModel: DictionaryViewModel,
    state: DictionaryUIState,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    SharedTransitionLayout {
        AnimatedContent(
            targetState = state.selectedSearchResult,
            transitionSpec = {
                (fadeIn(animationSpec = tween(500))
                        + scaleIn(initialScale = .8f, animationSpec = tween(600)))
                    .togetherWith(fadeOut(animationSpec = tween(90)))
            },
        ) { selectedSearchResult ->
            if (selectedSearchResult != null) {
                DefinitionCard (
                    viewModel = viewModel,
                    result = selectedSearchResult,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 8.dp)
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(
                                key = selectedSearchResult.entry.id!!
                            ),
                            animatedVisibilityScope = this@AnimatedContent,
                        )
                )
            }
            else {
                LazyColumn (
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp)
                ) {
                    items(
                        count = state.searchResults.size,
                        key = { index -> state.searchResults[index].entry.id!! }
                    ) { index ->
                        ResultCard (
                            result = state.searchResults[index],
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .clickable(
                                    enabled = state.selectedSearchResult == null,
                                    onClick = {
                                        viewModel.selectSearchResult(
                                            selected = state.searchResults[index]
                                        )
                                    }
                                )
                                .sharedElement(
                                    sharedContentState = rememberSharedContentState(
                                        key = state.searchResults[index].entry.id!!
                                    ),
                                    animatedVisibilityScope = this@AnimatedContent
                                )
                        )
                    }
                }
            }
        }
    }
}

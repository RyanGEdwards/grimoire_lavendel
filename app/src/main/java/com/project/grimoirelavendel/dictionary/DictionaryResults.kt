package com.project.grimoirelavendel.dictionary

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.project.grimoirelavendel.data.classes.DictionaryEntryWithDetails
import com.project.grimoirelavendel.data.enums.DictionaryTags

@Composable
fun DictionaryResults (
    searchResults: List<DictionaryEntryWithDetails>,
    listState: LazyListState = rememberLazyListState(),
    expandSearchResult: (DictionaryEntryWithDetails) -> Unit,
    isExpanded: Boolean,
    selectedSearchResult: DictionaryEntryWithDetails?,
    collapseSearchResult: () -> Unit
) {
    SharedTransitionLayout {
        AnimatedContent(
            targetState = selectedSearchResult,
            transitionSpec = {
                (fadeIn(animationSpec = tween(500))
                    + scaleIn(initialScale = .8f, animationSpec = tween(600)))
                    .togetherWith(fadeOut(animationSpec = tween(90)))
            },
        ) { result ->
            if (result != null) {
                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 8.dp)
                ) {
                    DictionaryEntry(
                        entry = result,
                        collapseSearchResult = collapseSearchResult,
                        modifier = Modifier
                            .fillMaxSize()
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(key = result.entry.id),
                                animatedVisibilityScope = this@AnimatedContent,
                            )
                    )
                }
            }
            else {
                LazyColumn (
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp)
                ) {
                    items(
                        count = searchResults.size,
                        key = { index -> searchResults[index].entry.id }
                    ) { index ->
                        DictionaryEntryCard(
                            entry = searchResults[index],
                            expandSearchResult = expandSearchResult,
                            isExpanded = isExpanded,
                            modifier = Modifier
                                .fillMaxWidth()
                                .sharedElement(
                                    sharedContentState = rememberSharedContentState(
                                        key = searchResults[index].entry.id
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

@Composable
fun DictionaryEntryCard (
    entry: DictionaryEntryWithDetails,
    expandSearchResult: (DictionaryEntryWithDetails) -> Unit,
    isExpanded: Boolean,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier
            .padding(vertical = 4.dp)
            .clickable(
                enabled = !isExpanded,
                onClick = { expandSearchResult(entry) }
            ),
        elevation = CardDefaults.cardElevation(),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Row (
            modifier = Modifier
                .padding(all = 8.dp)
                .padding(start = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                modifier = Modifier.align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TermDisplay(entry = entry)
                Text(
                    text = entry.glossaries.joinToString { it.glossaryText },
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun DictionaryEntry (
    entry: DictionaryEntryWithDetails,
    collapseSearchResult: () -> Unit,
    modifier: Modifier
) {
    Card (
        modifier = modifier,
        elevation = CardDefaults.cardElevation(),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column (
            modifier = Modifier
                .padding(all = 8.dp)
                .padding(horizontal = 12.dp)
                .verticalScroll(state = rememberScrollState())
                .weight(1f)
        ) {
            TermDisplay(entry = entry)

            val tags = remember(entry.entry.termTags) {
                DictionaryTags.parseToLabels(entry.entry.termTags)
            }
            if (tags.isNotEmpty()) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    thickness = 2.dp
                )
                TermTags(tags = tags)
            }

            if (entry.glossaries.isNotEmpty()) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    thickness = 2.dp
                )
                TermGlossaries(entry = entry)
            }

            if (entry.examples.isNotEmpty()) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    thickness = 2.dp
                )

                TermExamples(entry = entry)
            }
        }
        Button (
            onClick = { collapseSearchResult() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
        ) {
            Text(
                text = "Close",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize
            )
        }
    }
}

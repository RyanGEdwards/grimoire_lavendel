package com.project.grimoirelavendel.dictionary

import android.app.Application
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.grimoirelavendel.data.classes.DictionaryDB
import com.project.grimoirelavendel.data.classes.DictionaryEntry
import com.project.grimoirelavendel.network.DictionaryRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DictionaryViewModel(application: Application) : AndroidViewModel(application) {
    private val database = DictionaryDB.getInstance(application)
    private val repository = DictionaryRepository(database = database)

    var searchResults by mutableStateOf<List<DictionaryEntry>>(emptyList<DictionaryEntry>())
    private var ongoingSearch: Job? = null

    fun searchDictionary(query: String) {
        if (ongoingSearch != null) ongoingSearch?.cancel()

        ongoingSearch = viewModelScope.launch {
            searchResults = repository.searchDictionary(query)
        }
    }

    fun clearSearch() {
        searchResults = emptyList()
    }

    var searchValue by mutableStateOf("")
}

@Composable
fun DictionaryVM (
    viewModel: DictionaryViewModel = viewModel()
) {
    val listState = rememberLazyListState()
    val isSearchBarVisible by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset < 400
        }
    }

    Column {
        AnimatedVisibility(
            visible = isSearchBarVisible,
            enter = expandVertically(animationSpec = tween(durationMillis = 300)),
            exit = shrinkVertically(animationSpec = tween(durationMillis = 500))
        ) {
            SearchBar(
                searchValue = viewModel.searchValue,
                searchInputChange = {
                    viewModel.searchValue = it
                    viewModel.searchDictionary(it)
                },
                clearSearch = {
                    viewModel.clearSearch()
                    viewModel.searchValue = ""
                }
            )
        }
        if (viewModel.searchValue.isNotEmpty()) {
            DictionaryResults(
                searchResults = viewModel.searchResults,
                listState = listState
            )
        }
    }
}
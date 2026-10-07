package com.project.grimoirelavendel.dictionary

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.grimoirelavendel.data.classes.DictionaryDB
import com.project.grimoirelavendel.data.classes.DictionaryEntry
import com.project.grimoirelavendel.network.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class DictionaryViewModel(application: Application) : AndroidViewModel(application) {
    private val database = DictionaryDB.getInstance(application)
    private val repository = DictionaryRepository(database = database)

    var searchResults by mutableStateOf<Flow<List<DictionaryEntry>>>(
        repository.searchDictionary("")
    )

    fun searchDictionary(query: String) {
        searchResults = repository.searchDictionary(query)
    }

    fun clearSearch() {
        searchResults = emptyFlow()
    }

    var searchValue by mutableStateOf("")
}

@Composable
fun DictionaryVM (
    viewModel: DictionaryViewModel = viewModel()
) {
    Column {
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
        DictionaryResults(
            searchResultsFlow = viewModel.searchResults
        )
    }
}
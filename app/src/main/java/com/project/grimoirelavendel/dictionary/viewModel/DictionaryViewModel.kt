package com.project.grimoirelavendel.dictionary.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.project.grimoirelavendel.data.localDatabase.GrimoireDB
import com.project.grimoirelavendel.data.localDatabase.GrimoireRepository
import com.project.grimoirelavendel.data.localDatabase.dataTypes.SearchResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DictionaryViewModel (application: Application): AndroidViewModel(application) {
    // Repository Setup
    private val database = GrimoireDB.getInstance(application)
    private val repository = GrimoireRepository(database = database)

    // Variables
    private val _uiState = MutableStateFlow(DictionaryUIState())
    val uiState: StateFlow<DictionaryUIState> = _uiState.asStateFlow()
    private var searchJob: Job? = null

    // User Actions
    fun updateSearchValue (value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = value,
                isLoading = true
            )
        }
        searchJob = viewModelScope.launch {
            val results = repository.search(value)
            _uiState.update { currentState ->
                currentState.copy(
                    searchResults = results,
                    isLoading = false
                )
            }
        }
    }

    fun clearSearch () {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = "",
                searchResults = emptyList(),
                selectedSearchResult = null
            )
        }
    }

    fun selectSearchResult (selected: SearchResult) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedSearchResult = selected
            )
        }
    }

    fun deselectSearchResult () {
        _uiState.update { currentState ->
            currentState.copy(
                selectedSearchResult = null
            )
        }
    }
}

package com.project.grimoirelavendel.dictionary.viewModel

import com.project.grimoirelavendel.data.localDatabase.dataTypes.SearchResult

data class DictionaryUIState (
    val searchQuery: String = "",
    val searchResults: List<SearchResult> = emptyList(),
    val selectedSearchResult: SearchResult? = null,

    val isLoading: Boolean = false,
    val isError: Boolean = false
)

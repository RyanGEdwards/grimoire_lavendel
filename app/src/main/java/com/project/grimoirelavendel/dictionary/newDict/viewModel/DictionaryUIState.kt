package com.project.grimoirelavendel.dictionary.newDict.viewModel

import com.project.grimoirelavendel.data.newData.dataTypes.SearchResult

data class DictionaryUIState (
    val searchQuery: String = "",
    val searchResults: List<SearchResult> = emptyList(),
    val selectedSearchResult: SearchResult? = null,

    val isLoading: Boolean = false,
    val isError: Boolean = false
)
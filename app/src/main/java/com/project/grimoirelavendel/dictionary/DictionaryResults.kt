package com.project.grimoirelavendel.dictionary

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.grimoirelavendel.data.classes.DictionaryEntry
import kotlinx.coroutines.flow.Flow

@Composable
fun DictionaryResults (
    searchResultsFlow: Flow<List<DictionaryEntry>>
) {
    val searchResults = searchResultsFlow.collectAsState(initial = emptyList())

    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        items(
            count = searchResults.value.size,
        ) {
            DictionaryEntryCard(
                entry = searchResults.value[it]
            )
        }
    }
}

@Composable
fun DictionaryEntryCard(
    entry: DictionaryEntry
) {
    Text(text = entry.term)
}

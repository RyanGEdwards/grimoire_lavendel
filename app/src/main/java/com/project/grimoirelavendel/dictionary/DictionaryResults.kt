package com.project.grimoirelavendel.dictionary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.grimoirelavendel.data.classes.DictionaryEntry

@Composable
fun DictionaryResults (
    searchResults: List<DictionaryEntry>
) {

    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .padding(horizontal = 2.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        items(
            count = searchResults.size
        ) {
            DictionaryEntryCard(
                entry = searchResults[it]
            )
        }
    }
}

@Composable
fun DictionaryEntryCard(
    entry: DictionaryEntry
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp)
            .padding(vertical = 4.dp),
        onClick = { }
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
                if (entry.term == entry.reading) {
                    Text(
                        text = entry.term,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize
                    )
                }
                else {
                    Text(
                        text = entry.term + " - " + entry.reading,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize
                    )
                }
                Text(
                    text = entry.definitions.definitions[0].meanings.joinToString(
                        separator = ", ",
                        limit = 5,
                        truncated = "..."
                    ),
                    maxLines = 3
                )
            }
        }
    }
}
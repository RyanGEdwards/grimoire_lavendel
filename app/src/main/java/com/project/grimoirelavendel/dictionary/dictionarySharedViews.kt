package com.project.grimoirelavendel.dictionary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.grimoirelavendel.data.classes.DictionaryEntryWithDetails

@Composable
fun TermDisplay (
    entry: DictionaryEntryWithDetails,
    modifier: Modifier = Modifier
) {
    if (entry.entry.term == entry.entry.reading) {
        Text(
            text = entry.entry.term,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            lineHeight = MaterialTheme.typography.headlineLarge.lineHeight,
            modifier = modifier
        )
    }
    else {
        Text(
            text = entry.entry.term + " - " + entry.entry.reading,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            lineHeight = MaterialTheme.typography.headlineLarge.lineHeight,
            modifier = modifier
        )
    }
}

@Composable
fun TermTags (
    tags: List<String>,
    modifier: Modifier = Modifier
) {
    FlowRow (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        for (tag in tags) {
            SuggestionChip (
                onClick = {},
                label = {
                    Text (
                        text = tag,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            )
        }
    }
}

@Composable
fun TermGlossaries (
    entry: DictionaryEntryWithDetails,
    modifier: Modifier = Modifier
) {
    var i = 1
    Column (modifier = modifier) {
        Text (
            text = "Meanings",
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            lineHeight = MaterialTheme.typography.headlineMedium.lineHeight
        )
        for (glossary in entry.glossaries) {
            Row {
                Text (
                    text = "${i}.",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Text (
                    glossary.glossaryText,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            i++
        }
    }
}

@Composable
fun TermExamples (
    entry: DictionaryEntryWithDetails,
    modifier: Modifier = Modifier
) {
    var i = 1
    Column(modifier = modifier) {
        Text(
            text = "Examples",
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            lineHeight = MaterialTheme.typography.headlineMedium.lineHeight
        )
        for (example in entry.examples) {
            Text(
                text = i.toString() + ". " + example.exampleText,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                modifier = Modifier.padding(start = 4.dp)
            )
            i++
        }
    }
}

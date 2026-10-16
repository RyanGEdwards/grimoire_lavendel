package com.project.grimoirelavendel.dictionary.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.grimoirelavendel.data.localDatabase.dataTypes.SearchResult
import com.project.grimoirelavendel.data.localDatabase.dataTypes.alternateSpellings
import com.project.grimoirelavendel.data.localDatabase.dataTypes.consolidatedReadingsList
import com.project.grimoirelavendel.data.localDatabase.dataTypes.consolidatedSensesList
import com.project.grimoirelavendel.dictionary.viewModel.DictionaryViewModel

@Composable
fun DefinitionCard(
    viewModel: DictionaryViewModel,
    result: SearchResult,
    modifier: Modifier = Modifier
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
            TermDisplay(
                term = result.entry.term,
                reading = result.entry.reading
            )

            if (result.alternateSpellings.isNotEmpty()) {
                KanjiDisplay(result.alternateSpellings)
            }

            if (result.readings.isNotEmpty()) {
                ReadingsDisplay(
                    readings = result.consolidatedReadingsList
                )
            }

            if (result.senses.isNotEmpty()) {
                SenseDisplay(
                    senses = result.consolidatedSensesList
                )
            }
        }

        Button (
            onClick = { viewModel.deselectSearchResult() },
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

// Primary Word
@Composable
fun TermDisplay (
    term: String,
    reading: String,
    modifier: Modifier = Modifier
) {
    WordReadingPair(
        term = term,
        reading = reading,
        modifier = modifier
    )

    HorizontalDivider(
        modifier = Modifier.padding(vertical = 8.dp),
        thickness = 4.dp
    )
}

// Alternate Spellings
@Composable
fun KanjiDisplay (
    alternateSpellings: List<String>,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Other Spellings",
        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        lineHeight = MaterialTheme.typography.headlineSmall.lineHeight,
        modifier = modifier
    )

    alternateSpellings.forEach {
        Text(
            text = "- $it",
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
            modifier = modifier
        )
    }

    HorizontalDivider(
        modifier = Modifier.padding(vertical = 8.dp),
        thickness = 1.dp
    )
}

// Kana reading
@Composable
fun ReadingsDisplay (
    readings: List<String>,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Readings",
        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        lineHeight = MaterialTheme.typography.headlineSmall.lineHeight,
        modifier = modifier
    )

    readings.forEach {
        Text(
            text = "- $it",
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
        )
    }

    HorizontalDivider(
        modifier = Modifier.padding(vertical = 8.dp),
        thickness = 1.dp
    )
}

// Word information (meaning, part of speech, etc.)
@Composable
fun SenseDisplay (
    senses: List<String>,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Meaning",
        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        lineHeight = MaterialTheme.typography.headlineSmall.lineHeight,
        modifier = modifier
    )

    senses.forEach {
        Text(
            text = "- $it",
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
        )
    }

    HorizontalDivider(
        modifier = Modifier.padding(vertical = 8.dp),
        thickness = 1.dp
    )
}

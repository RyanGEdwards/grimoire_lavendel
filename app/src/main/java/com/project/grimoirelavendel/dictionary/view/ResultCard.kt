package com.project.grimoirelavendel.dictionary.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.project.grimoirelavendel.data.localDatabase.dataTypes.SearchResult
import com.project.grimoirelavendel.data.localDatabase.dataTypes.formattedGlosses

@Composable
fun ResultCard (
    result: SearchResult,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier,
        elevation = CardDefaults.cardElevation(),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Row (
            modifier = Modifier
                .padding(all = 8.dp)
                .padding(start = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                modifier = Modifier.align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                WordReadingPair(
                    term = result.entry.term,
                    reading = result.entry.reading
                )

                GlossaryOverview(
                    glossary = result.formattedGlosses
                )
            }
        }
    }
}

@Composable
fun WordReadingPair (
    term: String,
    reading: String,
    modifier: Modifier = Modifier
) {
    if (term == reading) {
        Text(
            text = term,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            lineHeight = MaterialTheme.typography.headlineLarge.lineHeight,
        )
    }
    else {
        Text(
            text = "$term - $reading",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            lineHeight = MaterialTheme.typography.headlineLarge.lineHeight,
        )
    }
}

@Composable
fun GlossaryOverview (
    glossary: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = glossary,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis
    )
}

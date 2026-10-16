package com.project.grimoirelavendel.dictionary.newDict.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.grimoirelavendel.data.newData.dataTypes.SearchResult
import com.project.grimoirelavendel.dictionary.newDict.viewModel.DictionaryViewModel

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
        ) {}
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
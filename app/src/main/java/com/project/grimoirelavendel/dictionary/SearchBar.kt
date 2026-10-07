package com.project.grimoirelavendel.dictionary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar (
    searchValue: String,
    searchInputChange: (String) -> Unit,
    clearSearch: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .padding(bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = searchValue,
            onValueChange = searchInputChange,
            label = { Text("Search") },
            singleLine = true,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .padding(end = 8.dp)
        )
        Button(
            onClick = clearSearch,
            content = { Text("Clear") },
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

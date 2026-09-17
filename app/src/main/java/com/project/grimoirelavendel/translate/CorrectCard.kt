package com.project.grimoirelavendel.translate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.grimoirelavendel.data.CorrectResponse

@Composable
fun CorrectCard (
    expanded: Boolean,
    onClick: () -> Unit,
    correctInput: String,
    correctInputChange: (String) -> Unit,
    corrections: CorrectResponse,
    correctButtonClick: () -> Unit
) {
    val iconRotationDegrees by animateFloatAsState(
        targetValue = if (expanded) 0f else -90f,
        animationSpec = tween(
            durationMillis = 200,
            easing = FastOutSlowInEasing
        )
    )
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable { onClick() }
    ) {
        CardHeader(
            title = "Correct Text",
            expanded = expanded,
            iconRotationDegrees = iconRotationDegrees
        )
        CorrectCardContents(
            expanded = expanded,
            correctInput = correctInput,
            correctInputChange = correctInputChange,
            corrections = corrections,
            correctButtonClick = correctButtonClick
        )
    }
}

@Composable
fun CorrectCardContents(
    expanded: Boolean,
    correctInput: String,
    correctInputChange: (String) -> Unit,
    corrections: CorrectResponse,
    correctButtonClick: () -> Unit
) {
    AnimatedVisibility(
        visible = expanded,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    enabled = false,
                    interactionSource = null,
                    indication = null,
                    onClick = { }
                )
        ) {
            OutlinedTextField(
                value = correctInput,
                onValueChange = { correctInputChange(it) },
                label = { Text("Text to Correct") },
                placeholder = { Text("Input text to correct") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                minLines = 3
            )
            Button(
                onClick = { correctButtonClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp)
            ) {
                Text(text = "Correct")
            }
            DeepLCorrectResultsDisplay(
                corrections = corrections,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun DeepLCorrectResultsDisplay(
    corrections: CorrectResponse,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        for (correction in corrections.improvements) {
            DeepLResultRow(
                text = correction.text
            )
        }
    }
}

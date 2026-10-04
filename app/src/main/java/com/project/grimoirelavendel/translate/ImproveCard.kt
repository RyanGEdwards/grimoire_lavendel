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
import com.project.grimoirelavendel.data.classes.ImproveResponse

@Composable
fun ImproveCard (
    expanded: Boolean,
    onClick: () -> Unit,
    improveInput: String,
    improveInputChange: (String) -> Unit,
    improvements: ImproveResponse,
    improveButtonClick: () -> Unit
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
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        CardHeader(
            title = "Improve Text",
            expanded = expanded,
            iconRotationDegrees = iconRotationDegrees
        )
        ImproveCardContents(
            expanded = expanded,
            improveInput = improveInput,
            improveInputChange = improveInputChange,
            improvements = improvements,
            improveButtonClick = improveButtonClick
        )
    }
}

@Composable
fun ImproveCardContents (
    expanded: Boolean,
    improveInput: String,
    improveInputChange: (String) -> Unit,
    improvements: ImproveResponse,
    improveButtonClick: () -> Unit
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
                value = improveInput,
                onValueChange = { improveInputChange(it) },
                label = { Text("Text to Improve") },
                placeholder = { Text("Input text to improve") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp)
                    .padding(bottom = 4.dp),
                minLines = 3
            )
            Button(
                onClick = { improveButtonClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 4.dp)
            ) {
                Text(text = "Improve")
            }
            DeepLImproveResultsDisplay(
                improvements = improvements,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun DeepLImproveResultsDisplay(
    improvements: ImproveResponse,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        for (improvement in improvements.improvements) {
            DeepLResultRow(
                text = improvement.text
            )
        }
    }
}

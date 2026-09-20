package com.project.grimoirelavendel.translate
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

@Composable
fun CardHeader (
    title: String,
    expanded: Boolean,
    iconRotationDegrees: Float
) {
    Row {
        Text (
            text = title,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(vertical = 24.dp),
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.ExpandMore,
            contentDescription = if (expanded) "Collapse" else "Expand",
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(horizontal = 16.dp)
                .rotate(iconRotationDegrees)
        )
    }
}

@Composable
fun LanguageSourceTargetRow (
    sourceLanguage: String,
    targetLanguage: String,
    onClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.CenterVertically),
            contentAlignment = Alignment.CenterStart,
        ) { Text (text = sourceLanguage) }
        Box(
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ) {
            Button (
                onClick = { onClick() },
            ) {
                Icon (
                    imageVector = Icons.Default.SyncAlt,
                    contentDescription = "Swap Language"
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.CenterVertically),
            contentAlignment = Alignment.CenterEnd
        ) { Text (text = targetLanguage) }
    }
}

@Composable
fun DeepLResultRow (
    text: String
) {
    Text (
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        textAlign = androidx.compose.ui.text.style.TextAlign.Center
    )
}

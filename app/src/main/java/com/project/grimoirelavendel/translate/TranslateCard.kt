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
import com.project.grimoirelavendel.data.TranslationResponse

@Composable
fun TranslateCard (
    expanded: Boolean,
    onClick: () -> Unit,
    translateInput: String,
    translateInputChange: (String) -> Unit,
    translateSourceLanguage: String,
    translateTargetLanguage: String,
    translateSourceLanguageChange: () -> Unit,
    translations: TranslationResponse,
    translateButtonClick: () -> Unit
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
            title = "Translate Text",
            expanded = expanded,
            iconRotationDegrees = iconRotationDegrees
        )
        TranslateCardContents(
            expanded = expanded,
            translateInput = translateInput,
            translateInputChange = translateInputChange,
            translateSourceLanguage = translateSourceLanguage,
            translateTargetLanguage = translateTargetLanguage,
            translateSourceLanguageChange = translateSourceLanguageChange,
            translations = translations,
            translateButtonClick = translateButtonClick
        )
    }
}

@Composable
fun TranslateCardContents (
    expanded: Boolean,
    translateInput: String,
    translateInputChange: (String) -> Unit,
    translateSourceLanguage: String,
    translateTargetLanguage: String,
    translateSourceLanguageChange: () -> Unit,
    translations: TranslationResponse,
    translateButtonClick: () -> Unit
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
                value = translateInput,
                onValueChange = { translateInputChange(it) },
                label = { Text("Source Text") },
                placeholder = { Text("Input text to translate") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp)
                    .padding(bottom = 2.dp),
                minLines = 3
            )
            LanguageSourceTargetRow (
                sourceLanguage = translateSourceLanguage,
                targetLanguage = translateTargetLanguage,
                onClick = translateSourceLanguageChange
            )
            Button(
                onClick = { translateButtonClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 4.dp)
            ) {
                Text(text = "Translate")
            }
            DeepLTranslateResultsDisplay(
                translations = translations,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun DeepLTranslateResultsDisplay (
    translations: TranslationResponse,
    modifier: Modifier
) {
    Column (
        modifier = modifier
    ) {
        for (translation in translations.translations) {
            DeepLResultRow(
                text = translation.text
            )
        }
    }
}

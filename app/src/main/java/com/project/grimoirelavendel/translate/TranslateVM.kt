package com.project.grimoirelavendel.translate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewModelScope
import com.project.grimoirelavendel.data.classes.CorrectResponse
import com.project.grimoirelavendel.data.classes.ImproveResponse
import com.project.grimoirelavendel.data.enums.Languages
import com.project.grimoirelavendel.data.classes.TranslationResponse
import com.project.grimoirelavendel.network.DeepLRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TranslateViewModel : ViewModel() {
    // Translate
    var translateExpanded by mutableStateOf(true)
    var translationResponse by mutableStateOf(TranslationResponse(emptyList()))
    var translateInput by mutableStateOf("")
    var translateSourceLanguage by mutableStateOf(Languages.ENGLISH)
    var translateTargetLanguage by mutableStateOf(Languages.JAPANESE)

    fun translateSourceLanguageChange() {
        val temp = translateSourceLanguage
        translateSourceLanguage = translateTargetLanguage
        translateTargetLanguage = temp
    }

    fun translateButtonClick(repo: DeepLRepository): Job {
        if (translateInput.isEmpty()) { return Job() }
        return viewModelScope.launch {
            translationResponse = repo.getTranslations(
                textToTranslate = listOf(translateInput),
                targetLanguage = translateTargetLanguage.deeplCode
            )
        }
    }

    // Improve
    var improveExpanded by mutableStateOf(false)
    var improvementResponse by mutableStateOf(ImproveResponse(emptyList()))
    var improveInput by mutableStateOf("")

    fun improveButtonClick(repo: DeepLRepository): Job {
        if (improveInput.isEmpty()) { return Job() }
        return viewModelScope.launch {
            improvementResponse = repo.getImprovements(
                textToImprove = listOf(improveInput)
            )
        }
    }

    // Correct
    var correctExpanded by mutableStateOf(false)
    var correctResponse by mutableStateOf(CorrectResponse(emptyList()))
    var correctInput by mutableStateOf("")

    fun correctButtonClick(repo: DeepLRepository): Job {
        if (correctInput.isEmpty()) { return Job() }
        return viewModelScope.launch {
            correctResponse = repo.getCorrections(
                textToCorrect = listOf(correctInput)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslateVM (
    repo : DeepLRepository,
    viewModel: TranslateViewModel = viewModel()
) {
    val translateInputChange = { newInput: String -> viewModel.translateInput = newInput }
    val improveInputChange = { newInput: String -> viewModel.improveInput = newInput }
    val correctInputChange = { newInput: String -> viewModel.correctInput = newInput }
    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        TranslateCard (
            expanded = viewModel.translateExpanded,
            onClick = {
                if (!viewModel.translateExpanded) {
                    viewModel.translateExpanded = true
                    viewModel.improveExpanded = false
                    viewModel.correctExpanded = false
                }
            },
            translateInput = viewModel.translateInput,
            translateInputChange = translateInputChange,
            translateSourceLanguage = viewModel.translateSourceLanguage.language,
            translateTargetLanguage = viewModel.translateTargetLanguage.language,
            translateSourceLanguageChange = { viewModel.translateSourceLanguageChange() },
            translations = viewModel.translationResponse,
            translateButtonClick = { viewModel.translateButtonClick(repo) }
        )
        ImproveCard (
            expanded = viewModel.improveExpanded,
            onClick = {
                if (!viewModel.improveExpanded) {
                    viewModel.translateExpanded = false
                    viewModel.improveExpanded = true
                    viewModel.correctExpanded = false
                }
            },
            improveInput = viewModel.improveInput,
            improveInputChange = improveInputChange,
            improvements = viewModel.improvementResponse,
            improveButtonClick = { viewModel.improveButtonClick(repo) }
        )
        CorrectCard (
            expanded = viewModel.correctExpanded,
            onClick = {
                if (!viewModel.correctExpanded) {
                    viewModel.translateExpanded = false
                    viewModel.improveExpanded = false
                    viewModel.correctExpanded = true
                }
            },
            correctInput = viewModel.correctInput,
            correctInputChange = correctInputChange,
            corrections = viewModel.correctResponse,
            correctButtonClick = { viewModel.correctButtonClick(repo) }
        )
    }
}

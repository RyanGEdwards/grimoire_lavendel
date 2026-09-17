package com.project.grimoirelavendel.translate

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewModelScope
import com.project.grimoirelavendel.data.Languages
import com.project.grimoirelavendel.data.TranslationResponse
import com.project.grimoirelavendel.network.DeepLRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TranslateViewModel : ViewModel() {
    var translateExpanded by mutableStateOf(false)
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
        return viewModelScope.launch {
            translationResponse = repo.getTranslations(
                textToTranslate = listOf(translateInput),
                targetLanguage = translateTargetLanguage.deeplCode
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

    Column {
        TranslateCard (
            expanded = viewModel.translateExpanded,
            onClick = { viewModel.translateExpanded = !viewModel.translateExpanded },
            translateInput = viewModel.translateInput,
            translateInputChange = translateInputChange,
            translateSourceLanguage = viewModel.translateSourceLanguage.language,
            translateTargetLanguage = viewModel.translateTargetLanguage.language,
            translateSourceLanguageChange = { viewModel.translateSourceLanguageChange() },
            translations = viewModel.translationResponse,
            translateButtonClick = { viewModel.translateButtonClick(repo) }
        )
    }
}

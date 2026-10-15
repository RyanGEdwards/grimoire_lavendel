package com.project.grimoirelavendel.network

import com.project.grimoirelavendel.data.classes.CorrectRequest
import com.project.grimoirelavendel.data.classes.CorrectResponse
import com.project.grimoirelavendel.data.classes.Environment
import com.project.grimoirelavendel.data.classes.ImproveRequest
import com.project.grimoirelavendel.data.classes.ImproveResponse
import com.project.grimoirelavendel.data.classes.TranslationRequest
import com.project.grimoirelavendel.data.classes.TranslationResponse

class DeepLRepository {
    val environment = Environment()
    val authHeaderValue = "DeepL-Auth-Key ${environment.apiKey}"
    private val deepLService = DeepLClient.deepLService

    suspend fun getTranslations (
        textToTranslate: List<String>,
        targetLanguage: String
    ): TranslationResponse {
        val requestBody = TranslationRequest(
            text = textToTranslate,
            target_lang = targetLanguage
        )

        val response = deepLService.translate(
            auth = authHeaderValue,
            request = requestBody
        )

        return response
    }

    suspend fun getImprovements (
        textToImprove: List<String>
    ): ImproveResponse {
        val requestBody = ImproveRequest(
            text = textToImprove
        )

        val response = deepLService.improve(
            auth = authHeaderValue,
            request = requestBody
        )

        return response
    }

    suspend fun getCorrections (
        textToCorrect: List<String>
    ): CorrectResponse {
        val requestBody = CorrectRequest(
            text = textToCorrect
        )

        val response = deepLService.correct(
            auth = authHeaderValue,
            request = requestBody
        )

        return response
    }
}

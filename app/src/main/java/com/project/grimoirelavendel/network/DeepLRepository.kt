package com.project.grimoirelavendel.network

import com.project.grimoirelavendel.data.CorrectRequest
import com.project.grimoirelavendel.data.CorrectResponse
import com.project.grimoirelavendel.data.Environment
import com.project.grimoirelavendel.data.ImproveRequest
import com.project.grimoirelavendel.data.ImproveResponse
import com.project.grimoirelavendel.data.TranslationRequest
import com.project.grimoirelavendel.data.TranslationResponse

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

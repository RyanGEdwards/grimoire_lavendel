package com.project.grimoirelavendel.network

import com.project.grimoirelavendel.data.CorrectRequest
import com.project.grimoirelavendel.data.CorrectResponse
import com.project.grimoirelavendel.data.ImproveRequest
import com.project.grimoirelavendel.data.ImproveResponse
import com.project.grimoirelavendel.data.TranslationRequest
import com.project.grimoirelavendel.data.TranslationResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface DeepLService {
    @POST("translate")
    suspend fun translate(
        @Header("Authorization") auth: String,
        @Header("Content-Type") contentType: String = "application/json",
        @Body request: TranslationRequest
    ): TranslationResponse

    @POST("write/rephrase")
    suspend fun improve(
        @Header("Authorization") auth: String,
        @Header("Content-Type") contentType: String = "application/json",
        @Body request: ImproveRequest
    ): ImproveResponse

    @POST("write/correct")
    suspend fun correct(
        @Header("Authorization") auth: String,
        @Header("Content-Type") contentType: String = "application/json",
        @Body request: CorrectRequest
    ): CorrectResponse
}

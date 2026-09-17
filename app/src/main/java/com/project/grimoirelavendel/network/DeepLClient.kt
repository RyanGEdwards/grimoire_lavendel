package com.project.grimoirelavendel.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

object DeepLClient {
        private const val BASE_URL = "https://api-free.deepl.com/v2/"
        private const val TIMEOUT = 10

        private val json = Json { ignoreUnknownKeys = true }

        private val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor (HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
            }).build()

        private val retrofit : Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(
                        json.asConverterFactory("application/json".toMediaType())
                )
                .build()

        val deepLService : DeepLService by lazy {
                retrofit.create(DeepLService::class.java)
        }
}

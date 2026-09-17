package com.project.grimoirelavendel.data

import kotlinx.serialization.Serializable

// Translation
@Serializable
data class TranslationRequest (
    val text: List<String>,
    val target_lang: String
)

@Serializable
data class TranslationResponse (
    val translations: List<Translation>
)

@Serializable
data class Translation(
    val detected_source_language: String,
    val text: String
)

// Improve
@Serializable
data class ImproveRequest (
    val text: List<String>
)

@Serializable
data class ImproveResponse (
    val improvements: List<Improvement>
)

// Correct
@Serializable
data class CorrectRequest (
    val text: List<String>
)

@Serializable
data class CorrectResponse (
    val improvements: List<Improvement>
)

// Shared
@Serializable
data class Improvement(
    val detected_source_language: String,
    val target_language: String,
    val text: String
)

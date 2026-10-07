package com.project.grimoirelavendel.data.classes

import kotlinx.serialization.Serializable

@Serializable
data class DefinitionItem (
    val meanings: List<String> = emptyList(),
    val partOfSpeech: List<String> = emptyList(),
    val tags: List<String> = emptyList()
)

@Serializable
data class DefinitionData (
    val definitions: List<DefinitionItem> = emptyList()
) {
}

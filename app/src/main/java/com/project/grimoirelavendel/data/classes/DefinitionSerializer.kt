package com.project.grimoirelavendel.data.classes

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class DefinitionSerializer {
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }

    @TypeConverter
    fun fromJson(jsonString: String?): DefinitionData {
        if (jsonString.isNullOrBlank()) return DefinitionData()

        return try {
            val element = json.parseToJsonElement(jsonString)
            val meanings = mutableListOf<String>()

            when (element) {
                is JsonArray -> {
                    element.jsonArray.forEach { item ->
                        extractText(item, meanings)
                    }
                }
                is JsonObject -> {
                    val array = element["definitions"] as? JsonArray
                        ?: element["items"] as? JsonArray
                        ?: element["glosses"] as? JsonArray

                    array?.forEach { item -> extractText(item, meanings) }
                }
                else -> {}
            }

            if (meanings.isEmpty()) {
                DefinitionData()
            } else {
                DefinitionData(definitions = listOf(DefinitionItem(meanings = meanings)))
            }
        } catch (e: Exception) {
            DefinitionData()
        }
    }

    private fun extractText(element: JsonElement, output: MutableList<String>) {
        when {
            runCatching { element.jsonPrimitive }.isSuccess -> {
                val content = element.jsonPrimitive.content
                if (content.isNotBlank()) output.add(content)
            }
            runCatching { element.jsonObject }.isSuccess -> {
                val obj = element.jsonObject
                when {
                    obj.containsKey("content") -> extractText(obj["content"]!!, output)
                    obj.containsKey("text") -> extractText(obj["text"]!!, output)
                }
            }
            runCatching { element.jsonArray }.isSuccess -> {
                element.jsonArray.forEach { extractText(it, output) }
            }
        }
    }

    @TypeConverter
    fun toJson(data: DefinitionData): String {
        return json.encodeToString(data)
    }
}

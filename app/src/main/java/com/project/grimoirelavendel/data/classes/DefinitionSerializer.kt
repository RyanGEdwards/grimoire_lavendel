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
                // Yomichan raw definition array: ["to eat", "to consume"]
                is JsonArray -> {
                    element.jsonArray.forEach { item ->
                        extractText(item, meanings)
                    }
                }
                // Structured object: {"definitions": [{"meanings": [...]}]}
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

    /** Recursively unwraps strings out of nested Yomichan JSON elements */
    private fun extractText(element: JsonElement, output: MutableList<String>) {
        when {
            // Plain string / text
            runCatching { element.jsonPrimitive }.isSuccess -> {
                val content = element.jsonPrimitive.content
                if (content.isNotBlank()) output.add(content)
            }
            // Yomichan object with a "content" or "text" key
            runCatching { element.jsonObject }.isSuccess -> {
                val obj = element.jsonObject
                when {
                    obj.containsKey("content") -> extractText(obj["content"]!!, output)
                    obj.containsKey("text") -> extractText(obj["text"]!!, output)
                }
            }
            // Nested array inside definition
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

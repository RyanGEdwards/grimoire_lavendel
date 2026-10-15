package com.project.grimoirelavendel.data.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "dictionary_entries",
    indices = [
        Index(value = ["reading"], name = "idx_reading"),
        Index(value = ["term"], name = "idx_term")
    ]
)
data class DictionaryEntry (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val term: String,
    val reading: String,

    @ColumnInfo(name = "definition_json")
    val definitionJson: String,
    val score: Int,

    @ColumnInfo(name = "term_tags")
    val termTags: String?
)

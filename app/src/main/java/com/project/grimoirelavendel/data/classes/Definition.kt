package com.project.grimoirelavendel.data.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Fts4(contentEntity = DictionaryEntry::class)
@Entity(tableName = "fts_definitions")
data class Definition(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val rowid: Int,
    @ColumnInfo(name = "definition_json")
    val definitionJson: String
)

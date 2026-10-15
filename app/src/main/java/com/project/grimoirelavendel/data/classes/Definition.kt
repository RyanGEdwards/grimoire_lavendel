package com.project.grimoirelavendel.data.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Fts4
@Entity(tableName = "fts_definitions")
data class Definition(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val rowid: Long,

    @ColumnInfo(name = "clean_glossary_text")
    val cleanGlossaryText: String
)

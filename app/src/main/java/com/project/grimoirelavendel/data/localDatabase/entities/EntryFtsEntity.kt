package com.project.grimoirelavendel.data.localDatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4

@Entity(tableName = "entries_fts")
@Fts4
data class EntryFtsEntity(
    @ColumnInfo(name = "entry_id") val entryId: Long,
    val term: String,
    val reading: String,
    val glosses: String
)

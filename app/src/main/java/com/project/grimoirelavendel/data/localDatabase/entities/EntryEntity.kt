package com.project.grimoirelavendel.data.localDatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entries")
data class EntryEntity(
    @PrimaryKey val id: Long?,
    val term: String,
    val reading: String,
    @ColumnInfo(name = "user_note") val userNote: String? = null
)

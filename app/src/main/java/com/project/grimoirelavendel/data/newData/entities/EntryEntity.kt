package com.project.grimoirelavendel.data.newData.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entries")
data class EntryEntity(
    @PrimaryKey val id: Long?, // Changed to Long? to match asset schema
    val term: String,
    val reading: String,
    @ColumnInfo(name = "user_note") val userNote: String? = null
)

package com.project.grimoirelavendel.data.newData.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entries")
data class EntryEntity(
    @PrimaryKey val id: Long,
    val term: String,
    val reading: String,
    val userNote: String? = null
)

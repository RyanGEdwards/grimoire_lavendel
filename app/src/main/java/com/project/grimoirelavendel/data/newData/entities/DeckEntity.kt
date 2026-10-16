package com.project.grimoirelavendel.data.newData.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "decks")
data class DeckEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "deck_id") val deckId: Long?,
    val name: String,
    val description: String?,
    @ColumnInfo(name = "created_at") val createdAt: Long
)

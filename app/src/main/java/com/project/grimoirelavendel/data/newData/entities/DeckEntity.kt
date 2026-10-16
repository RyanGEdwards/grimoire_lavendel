package com.project.grimoirelavendel.data.newData.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "decks")
data class DeckEntity(
    @PrimaryKey(autoGenerate = true) val deckId: Long = 0,
    val name: String,
    val description: String?,
    val createdAt: Long
)

package com.project.grimoirelavendel.data.newData.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "srs_cards",
    foreignKeys = [
        ForeignKey(entity = EntryEntity::class, parentColumns = ["id"], childColumns = ["entryId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = DeckEntity::class, parentColumns = ["deckId"], childColumns = ["deckId"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("entryId"), Index("deckId"), Index("dueDate")]
)
data class SrsCardEntity(
    @PrimaryKey(autoGenerate = true) val cardId: Long = 0,
    val entryId: Long,
    val deckId: Long,
    val state: Int = 0,
    val interval: Int = 0,
    val easeFactor: Float = 2.5f,
    val repetitions: Int = 0,
    val lapses: Int = 0,
    val learningStep: Int = 0,
    val isSuspended: Int = 0,
    val dateAdded: Long,
    val dueDate: Long,
    val lastReviewed: Long? = null
)

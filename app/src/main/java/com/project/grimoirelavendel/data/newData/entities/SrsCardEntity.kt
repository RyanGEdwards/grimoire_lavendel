package com.project.grimoirelavendel.data.newData.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "srs_cards",
    foreignKeys = [
        ForeignKey(
            entity = EntryEntity::class,
            parentColumns = ["id"],
            childColumns = ["entry_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DeckEntity::class,
            parentColumns = ["deck_id"],
            childColumns = ["deck_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("entry_id"),
        Index("deck_id"),
        Index("due_date"),
        Index(value = ["due_date"], name = "idx_srs_due"),
        Index(value = ["deck_id"], name = "idx_srs_deck")
    ]
)
data class SrsCardEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "card_id") val cardId: Long?,
    @ColumnInfo(name = "entry_id") val entryId: Long,
    @ColumnInfo(name = "deck_id") val deckId: Long,
    @ColumnInfo(defaultValue = "0") val state: Int = 0,
    @ColumnInfo(defaultValue = "0") val interval: Int = 0,
    @ColumnInfo(name = "ease_factor", defaultValue = "2.5") val easeFactor: Double = 2.5,
    @ColumnInfo(defaultValue = "0") val repetitions: Int = 0,
    @ColumnInfo(defaultValue = "0") val lapses: Int = 0,
    @ColumnInfo(name = "learning_step", defaultValue = "0") val learningStep: Int = 0,
    @ColumnInfo(name = "is_suspended", defaultValue = "0") val isSuspended: Int = 0,
    @ColumnInfo(name = "date_added") val dateAdded: Long,
    @ColumnInfo(name = "due_date") val dueDate: Long,
    @ColumnInfo(name = "last_reviewed") val lastReviewed: Long? = null
)

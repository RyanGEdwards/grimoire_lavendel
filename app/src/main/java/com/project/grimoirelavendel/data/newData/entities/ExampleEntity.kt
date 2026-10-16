package com.project.grimoirelavendel.data.newData.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "entry_examples",
    foreignKeys = [ForeignKey(
        entity = EntryEntity::class, 
        parentColumns = ["id"], 
        childColumns = ["entry_id"], 
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("entry_id"),
        Index(value = ["entry_id"], name = "idx_example_entry"),
        Index(value = ["entry_id"], name = "idx_examples_entry")
    ]
)
data class ExampleEntity(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "entry_id") val entryId: Long,
    @ColumnInfo(name = "sentence_ja") val sentenceJa: String,
    @ColumnInfo(name = "sentence_trans") val sentenceTrans: String
)

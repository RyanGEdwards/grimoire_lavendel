package com.project.grimoirelavendel.data.newData.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "entry_examples",
    foreignKeys = [ForeignKey(
        entity = EntryEntity::class, parentColumns = ["id"], childColumns = ["entryId"], onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("entryId")]
)
data class ExampleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val entryId: Long,
    val sentenceJa: String,
    val sentenceTrans: String
)

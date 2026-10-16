package com.project.grimoirelavendel.data.newData.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "senses",
    foreignKeys = [ForeignKey(
        entity = EntryEntity::class, parentColumns = ["id"], childColumns = ["entryId"], onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("entryId")]
)
data class SenseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val entryId: Long,
    val senseIndex: Int,
    val sInf: String?
)

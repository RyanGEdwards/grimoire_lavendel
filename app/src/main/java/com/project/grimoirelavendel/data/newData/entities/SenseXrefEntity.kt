package com.project.grimoirelavendel.data.newData.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "sense_xrefs",
    foreignKeys = [ForeignKey(
        entity = SenseEntity::class, parentColumns = ["id"], childColumns = ["senseId"], onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("senseId")]
)
data class SenseXrefEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val senseId: Long,
    val xref: String
)

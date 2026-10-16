package com.project.grimoirelavendel.data.newData.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "sense_xrefs",
    primaryKeys = ["sense_id", "xref"], // Changed to composite PK to match asset (no 'id' column found)
    foreignKeys = [ForeignKey(
        entity = SenseEntity::class, 
        parentColumns = ["id"], 
        childColumns = ["sense_id"], 
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("sense_id"), // index_sense_xrefs_sense_id
        Index(value = ["sense_id"], name = "idx_xref_sense")
    ]
)
data class SenseXrefEntity(
    @ColumnInfo(name = "sense_id") val senseId: Long,
    val xref: String
)

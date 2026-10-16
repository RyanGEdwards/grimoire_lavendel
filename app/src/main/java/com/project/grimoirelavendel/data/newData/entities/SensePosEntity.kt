package com.project.grimoirelavendel.data.newData.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "sense_pos",
    primaryKeys = ["sense_id", "pos"],
    foreignKeys = [ForeignKey(
        entity = SenseEntity::class, 
        parentColumns = ["id"], 
        childColumns = ["sense_id"], 
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index(value = ["sense_id"], name = "idx_pos_sense")
    ]
)
data class SensePosEntity(
    @ColumnInfo(name = "sense_id") val senseId: Long, // PK components must be non-nullable in Room
    val pos: String
)

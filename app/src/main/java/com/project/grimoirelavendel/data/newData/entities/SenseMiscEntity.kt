package com.project.grimoirelavendel.data.newData.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "sense_misc",
    primaryKeys = ["sense_id", "misc"],
    foreignKeys = [ForeignKey(
        entity = SenseEntity::class, 
        parentColumns = ["id"], 
        childColumns = ["sense_id"], 
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index(value = ["sense_id"], name = "idx_misc_sense")
    ]
)
data class SenseMiscEntity(
    @ColumnInfo(name = "sense_id") val senseId: Long,
    val misc: String
)

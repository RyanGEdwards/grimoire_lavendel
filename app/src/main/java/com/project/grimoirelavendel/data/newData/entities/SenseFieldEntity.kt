package com.project.grimoirelavendel.data.newData.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "sense_fields",
    primaryKeys = ["sense_id", "field"],
    foreignKeys = [ForeignKey(
        entity = SenseEntity::class, 
        parentColumns = ["id"], 
        childColumns = ["sense_id"], 
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index(value = ["sense_id"], name = "idx_field_sense")
    ]
)
data class SenseFieldEntity(
    @ColumnInfo(name = "sense_id") val senseId: Long,
    val field: String
)

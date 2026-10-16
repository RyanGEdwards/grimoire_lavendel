package com.project.grimoirelavendel.data.localDatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "sense_dialects",
    primaryKeys = ["sense_id", "dialect"],
    foreignKeys = [ForeignKey(
        entity = SenseEntity::class, 
        parentColumns = ["id"], 
        childColumns = ["sense_id"], 
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index(value = ["sense_id"], name = "idx_dialect_sense")
    ]
)
data class SenseDialectEntity(
    @ColumnInfo(name = "sense_id") val senseId: Long,
    val dialect: String
)

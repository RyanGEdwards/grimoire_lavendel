package com.project.grimoirelavendel.data.newData.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "sense_dialects",
    primaryKeys = ["senseId", "dialect"],
    foreignKeys = [ForeignKey(
        entity = SenseEntity::class, parentColumns = ["id"], childColumns = ["senseId"], onDelete = ForeignKey.CASCADE
    )]
)
data class SenseDialectEntity(
    val senseId: Long,
    val dialect: String
)

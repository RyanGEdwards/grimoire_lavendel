package com.project.grimoirelavendel.data.newData.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "sense_fields",
    primaryKeys = ["senseId", "field"],
    foreignKeys = [ForeignKey(
        entity = SenseEntity::class, parentColumns = ["id"], childColumns = ["senseId"], onDelete = ForeignKey.CASCADE
    )]
)
data class SenseFieldEntity(
    val senseId: Long,
    val field: String
)

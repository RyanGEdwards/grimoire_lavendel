package com.project.grimoirelavendel.data.newData.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "sense_pos",
    primaryKeys = ["senseId", "pos"],
    foreignKeys = [ForeignKey(
        entity = SenseEntity::class, parentColumns = ["id"], childColumns = ["senseId"], onDelete = ForeignKey.CASCADE
    )]
)
data class SensePosEntity(
    val senseId: Long,
    val pos: String
)

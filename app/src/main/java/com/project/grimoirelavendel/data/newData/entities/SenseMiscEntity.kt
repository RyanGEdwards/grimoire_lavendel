package com.project.grimoirelavendel.data.newData.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "sense_misc",
    primaryKeys = ["senseId", "misc"],
    foreignKeys = [ForeignKey(
        entity = SenseEntity::class, parentColumns = ["id"], childColumns = ["senseId"], onDelete = ForeignKey.CASCADE
    )]
)
data class SenseMiscEntity(
    val senseId: Long,
    val misc: String
)

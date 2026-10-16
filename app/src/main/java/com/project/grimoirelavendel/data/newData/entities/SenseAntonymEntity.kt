package com.project.grimoirelavendel.data.newData.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "sense_antonyms",
    primaryKeys = ["sense_id", "antonym"],
    foreignKeys = [ForeignKey(
        entity = SenseEntity::class, 
        parentColumns = ["id"], 
        childColumns = ["sense_id"], 
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("sense_id"),
        Index(value = ["sense_id"], name = "idx_antonym_sense")
    ]
)
data class SenseAntonymEntity(
    @ColumnInfo(name = "sense_id") val senseId: Long,
    val antonym: String
)

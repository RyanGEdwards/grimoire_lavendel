package com.project.grimoirelavendel.data.localDatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "sense_lsource",
    foreignKeys = [ForeignKey(
        entity = SenseEntity::class, 
        parentColumns = ["id"], 
        childColumns = ["sense_id"], 
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("sense_id"),
        Index(value = ["sense_id"], name = "idx_lsource_sense")
    ]
)
data class SenseLsourceEntity(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "sense_id") val senseId: Long,
    val lsource: String,
    val lang: String?
)

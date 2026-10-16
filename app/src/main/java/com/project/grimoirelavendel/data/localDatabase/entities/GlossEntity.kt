package com.project.grimoirelavendel.data.localDatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "glosses",
    foreignKeys = [ForeignKey(
        entity = SenseEntity::class, 
        parentColumns = ["id"], 
        childColumns = ["sense_id"], 
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("sense_id"),
        Index(value = ["sense_id"], name = "idx_gloss_sense"),
        Index(value = ["sense_id"], name = "idx_glosses_sense")
    ]
)
data class GlossEntity(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "sense_id") val senseId: Long,
    val gloss: String
)

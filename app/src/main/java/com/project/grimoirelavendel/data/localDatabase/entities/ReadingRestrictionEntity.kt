package com.project.grimoirelavendel.data.localDatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "reading_restrictions",
    primaryKeys = ["reading_id", "keb"],
    foreignKeys = [ForeignKey(
        entity = ReadingEntity::class, 
        parentColumns = ["id"], 
        childColumns = ["reading_id"], 
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("reading_id"),
        Index(value = ["reading_id"], name = "idx_restriction_reading"),
        Index(value = ["reading_id"], name = "idx_reading_restr")
    ]
)
data class ReadingRestrictionEntity(
    @ColumnInfo(name = "reading_id") val readingId: Long,
    val keb: String
)

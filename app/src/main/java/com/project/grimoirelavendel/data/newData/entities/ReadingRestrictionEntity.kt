package com.project.grimoirelavendel.data.newData.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "reading_restrictions",
    foreignKeys = [ForeignKey(
        entity = ReadingEntity::class, parentColumns = ["id"], childColumns = ["readingId"], onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("readingId")]
)
data class ReadingRestrictionEntity(
    val readingId: Long,
    val keb: String
)

package com.project.grimoirelavendel.data.localDatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "reading_elements",
    foreignKeys = [ForeignKey(
        entity = EntryEntity::class, 
        parentColumns = ["id"], 
        childColumns = ["entry_id"], 
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("entry_id"),
        Index(value = ["entry_id"], name = "idx_reading_entry")
    ]
)
data class ReadingEntity(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "entry_id") val entryId: Long,
    val reb: String,
    @ColumnInfo(name = "re_nokanji") val reNokanji: Int?,
    @ColumnInfo(name = "re_pri") val rePri: String?,
    @ColumnInfo(name = "re_inf") val reInf: String?
)

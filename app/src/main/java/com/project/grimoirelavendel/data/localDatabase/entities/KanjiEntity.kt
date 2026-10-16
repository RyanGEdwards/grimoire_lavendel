package com.project.grimoirelavendel.data.localDatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "kanji_elements",
    foreignKeys = [ForeignKey(
        entity = EntryEntity::class,
        parentColumns = ["id"],
        childColumns = ["entry_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("entry_id"),
        Index(name = "idx_kanji_entry", value = ["entry_id"])
    ]
)
data class KanjiEntity(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "entry_id") val entryId: Long,
    val keb: String,
    @ColumnInfo(name = "ke_pri") val kePri: String?,
    @ColumnInfo(name = "ke_inf") val keInf: String?
)

package com.project.grimoirelavendel.data.localDatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "senses",
    foreignKeys = [ForeignKey(
        entity = EntryEntity::class, 
        parentColumns = ["id"], 
        childColumns = ["entry_id"], 
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("entry_id"),
        Index(value = ["entry_id"], name = "idx_sense_entry"),
        Index(value = ["entry_id"], name = "idx_senses_entry") // Extra index from asset
    ]
)
data class SenseEntity(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "entry_id") val entryId: Long,
    @ColumnInfo(name = "sense_index") val senseIndex: Int,
    @ColumnInfo(name = "s_inf") val sInf: String?
)

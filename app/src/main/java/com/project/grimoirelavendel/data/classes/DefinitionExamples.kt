package com.project.grimoirelavendel.data.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "entry_glossaries",
    foreignKeys = [
        ForeignKey(
            entity = DictionaryEntry::class,
            parentColumns = ["id"],
            childColumns = ["entry_id"]
        )
    ],
    indices = [Index(value = ["entry_id"], name = "idx_glossary_entry")]
)
data class EntryGlossary(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "entry_id") val entryId: Long,
    @ColumnInfo(name = "sense_number") val senseNumber: Int,
    @ColumnInfo(name = "glossary_text") val glossaryText: String
)

@Entity(
    tableName = "entry_examples",
    foreignKeys = [
        ForeignKey(
            entity = DictionaryEntry::class,
            parentColumns = ["id"],
            childColumns = ["entry_id"]
        )
    ],
    indices = [Index(value = ["entry_id"], name = "idx_example_entry")]
)
data class EntryExample(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "entry_id") val entryId: Long,
    @ColumnInfo(name = "example_text") val exampleText: String
)

@Entity(
    tableName = "entry_cross_refs",
    foreignKeys = [
        ForeignKey(
            entity = DictionaryEntry::class,
            parentColumns = ["id"],
            childColumns = ["entry_id"]
        )
    ],
    indices = [Index(value = ["entry_id"], name = "idx_crossref_entry")]
)
data class EntryCrossRef(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "entry_id") val entryId: Long,
    @ColumnInfo(name = "target_term") val targetTerm: String
)

package com.project.grimoirelavendel.data.classes

import androidx.room.Embedded
import androidx.room.Relation

data class DictionaryEntryWithDetails(
    @Embedded
    val entry: DictionaryEntry,

    @Relation(
        parentColumn = "id",
        entityColumn = "entry_id"
    )
    val glossaries: List<EntryGlossary>,

    @Relation(
        parentColumn = "id",
        entityColumn = "entry_id"
    )
    val examples: List<EntryExample>,

    @Relation(
        parentColumn = "id",
        entityColumn = "entry_id"
    )
    val crossReferences: List<EntryCrossRef>
)

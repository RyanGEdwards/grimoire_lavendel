package com.project.grimoirelavendel.data.newData.dataTypes

import androidx.room.Embedded
import androidx.room.Relation
import com.project.grimoirelavendel.data.newData.entities.EntryEntity
import com.project.grimoirelavendel.data.newData.entities.ExampleEntity
import com.project.grimoirelavendel.data.newData.entities.GlossEntity
import com.project.grimoirelavendel.data.newData.entities.KanjiEntity
import com.project.grimoirelavendel.data.newData.entities.ReadingEntity
import com.project.grimoirelavendel.data.newData.entities.SenseAntonymEntity
import com.project.grimoirelavendel.data.newData.entities.SenseDialectEntity
import com.project.grimoirelavendel.data.newData.entities.SenseEntity
import com.project.grimoirelavendel.data.newData.entities.SenseFieldEntity
import com.project.grimoirelavendel.data.newData.entities.SenseLsourceEntity
import com.project.grimoirelavendel.data.newData.entities.SenseMiscEntity
import com.project.grimoirelavendel.data.newData.entities.SensePosEntity
import com.project.grimoirelavendel.data.newData.entities.SenseXrefEntity

data class SearchResult(
    @Embedded
    val entry: EntryEntity,

    @Relation(parentColumn = "id", entityColumn = "entry_id")
    val kanji: List<KanjiEntity>,

    @Relation(parentColumn = "id", entityColumn = "entry_id")
    val readings: List<ReadingEntity>,

    @Relation(parentColumn = "id", entityColumn = "entry_id", entity = SenseEntity::class)
    val senses: List<CompleteSense>,

    @Relation(parentColumn = "id", entityColumn = "entry_id")
    val examples: List<ExampleEntity>
)

data class CompleteSense(
    @Embedded
    val sense: SenseEntity,

    @Relation(parentColumn = "id", entityColumn = "sense_id")
    val glosses: List<GlossEntity>,

    @Relation(parentColumn = "id", entityColumn = "sense_id")
    val pos: List<SensePosEntity>,

    @Relation(parentColumn = "id", entityColumn = "sense_id")
    val fields: List<SenseFieldEntity>,

    @Relation(parentColumn = "id", entityColumn = "sense_id")
    val misc: List<SenseMiscEntity>,

    @Relation(parentColumn = "id", entityColumn = "sense_id")
    val dialects: List<SenseDialectEntity>,

    @Relation(parentColumn = "id", entityColumn = "sense_id")
    val xrefs: List<SenseXrefEntity>,

    @Relation(parentColumn = "id", entityColumn = "sense_id")
    val antonyms: List<SenseAntonymEntity>,

    @Relation(parentColumn = "id", entityColumn = "sense_id")
    val lsources: List<SenseLsourceEntity>
)

val SearchResult.formattedGlosses: String
    get() {
        val allGlosses: List<String> = this.senses.flatMap { sense: CompleteSense ->
            sense.glosses.map { glossEntity ->
                glossEntity.gloss
            }
        }
        return allGlosses.joinToString(", ")
    }

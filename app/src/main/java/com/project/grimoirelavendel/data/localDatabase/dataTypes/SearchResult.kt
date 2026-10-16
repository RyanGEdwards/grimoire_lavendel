package com.project.grimoirelavendel.data.localDatabase.dataTypes

import androidx.room.Embedded
import androidx.room.Relation
import com.project.grimoirelavendel.data.localDatabase.entities.EntryEntity
import com.project.grimoirelavendel.data.localDatabase.entities.ExampleEntity
import com.project.grimoirelavendel.data.localDatabase.entities.GlossEntity
import com.project.grimoirelavendel.data.localDatabase.entities.KanjiEntity
import com.project.grimoirelavendel.data.localDatabase.entities.ReadingEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SenseAntonymEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SenseDialectEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SenseEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SenseFieldEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SenseLsourceEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SenseMiscEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SensePosEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SenseXrefEntity

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

val SearchResult.alternateSpellings: List<String>
    get() { return kanji.drop(1).map { it.keb }}

val ReadingEntity.consolidatedReading: String
    get() {
        var reading = reb

        if (reNokanji != null) {
            if (reNokanji != 0) {
                reading += " (when written in only kana)"
            }
        }

        if (rePri != null) {
            reading += " (common)"
        }

        if (reInf != null) {
            reading += " (${reInf})"
        }

        return reading
    }

val SearchResult.consolidatedReadingsList: List<String>
    get() {
        return readings.map { it.consolidatedReading }
    }

val CompleteSense.consolidatedText: String
    get() {
        // 1. Baseline definition
        val definitions = glosses.joinToString("; ") { it.gloss }

        // 2. Collect each info item wrapped individually in parentheses
        val parentheticals = mutableListOf<String>()

        // Parts of Speech (e.g., (noun), (futsuumeishi))
        pos.forEach { p ->
            parentheticals.add("(${p.pos})")
        }

        // Domain / Field
        fields.forEach { f ->
            parentheticals.add("(Field: ${f.field})")
        }

        // Dialects
        dialects.forEach { d ->
            parentheticals.add("(Dialect: ${d.dialect})")
        }

        // Miscellaneous tags (e.g., (colloquial), (word usually written using kana alone))
        misc.forEach { m ->
            parentheticals.add("(${m.misc})")
        }

        // Sense Information / Notes (sInf)
        sense.sInf?.let { info ->
            if (info.isNotBlank()) {
                parentheticals.add("($info)")
            }
        }

        // Cross-references
        xrefs.forEach { x ->
            parentheticals.add("(See also: ${x.xref})")
        }

        // Antonyms
        antonyms.forEach { a ->
            parentheticals.add("(Antonym: ${a.antonym})")
        }

        // 3. Join baseline with space-separated parenthetical components
        return if (parentheticals.isNotEmpty()) {
            "$definitions ${parentheticals.joinToString(" ")}"
        } else {
            definitions
        }
    }

val SearchResult.consolidatedSensesList: List<String>
    get() = senses.map { it.consolidatedText }

package com.project.grimoirelavendel.data.localDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.grimoirelavendel.data.localDatabase.entities.DeckEntity
import com.project.grimoirelavendel.data.localDatabase.entities.EntryEntity
import com.project.grimoirelavendel.data.localDatabase.entities.EntryFtsEntity
import com.project.grimoirelavendel.data.localDatabase.entities.ExampleEntity
import com.project.grimoirelavendel.data.localDatabase.entities.GlossEntity
import com.project.grimoirelavendel.data.localDatabase.entities.KanjiEntity
import com.project.grimoirelavendel.data.localDatabase.entities.ReadingEntity
import com.project.grimoirelavendel.data.localDatabase.entities.ReadingRestrictionEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SenseAntonymEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SenseDialectEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SenseEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SenseFieldEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SenseLsourceEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SenseMiscEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SensePosEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SenseXrefEntity
import com.project.grimoirelavendel.data.localDatabase.entities.SrsCardEntity

@Database(
    entities = [
        EntryEntity::class,
        EntryFtsEntity::class,
        KanjiEntity::class,
        ReadingEntity::class,
        ReadingRestrictionEntity::class,
        SenseEntity::class,
        GlossEntity::class,
        SensePosEntity::class,
        SenseFieldEntity::class,
        SenseMiscEntity::class,
        SenseDialectEntity::class,
        SenseXrefEntity::class,
        SenseAntonymEntity::class,
        SenseLsourceEntity::class,
        ExampleEntity::class,
        DeckEntity::class,
        SrsCardEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class GrimoireDB : RoomDatabase() {
    abstract fun grimoireDAO(): GrimoireDAO

    companion object {
        @Volatile
        private var INSTANCE: GrimoireDB? = null

        private fun buildDatabase(context: Context): GrimoireDB {
            return Room.databaseBuilder(
                context.applicationContext,
                GrimoireDB::class.java,
                name = "grimoire_db"
            )
                .createFromAsset("grimoire.db")
                .fallbackToDestructiveMigration()
                .build()
        }

        suspend fun startup(context: Context) {
            kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                val db = getInstance(context)
                db.openHelper.writableDatabase.query("PRAGMA user_version;")
            }
        }

        fun getInstance(context: Context): GrimoireDB {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }
    }
}

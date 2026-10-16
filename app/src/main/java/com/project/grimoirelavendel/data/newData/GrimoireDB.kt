package com.project.grimoirelavendel.data.newData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.grimoirelavendel.data.newData.entities.DeckEntity
import com.project.grimoirelavendel.data.newData.entities.EntryEntity
import com.project.grimoirelavendel.data.newData.entities.EntryFtsEntity
import com.project.grimoirelavendel.data.newData.entities.ExampleEntity
import com.project.grimoirelavendel.data.newData.entities.GlossEntity
import com.project.grimoirelavendel.data.newData.entities.KanjiEntity
import com.project.grimoirelavendel.data.newData.entities.ReadingEntity
import com.project.grimoirelavendel.data.newData.entities.ReadingRestrictionEntity
import com.project.grimoirelavendel.data.newData.entities.SenseAntonymEntity
import com.project.grimoirelavendel.data.newData.entities.SenseDialectEntity
import com.project.grimoirelavendel.data.newData.entities.SenseEntity
import com.project.grimoirelavendel.data.newData.entities.SenseFieldEntity
import com.project.grimoirelavendel.data.newData.entities.SenseLsourceEntity
import com.project.grimoirelavendel.data.newData.entities.SenseMiscEntity
import com.project.grimoirelavendel.data.newData.entities.SensePosEntity
import com.project.grimoirelavendel.data.newData.entities.SenseXrefEntity
import com.project.grimoirelavendel.data.newData.entities.SrsCardEntity

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

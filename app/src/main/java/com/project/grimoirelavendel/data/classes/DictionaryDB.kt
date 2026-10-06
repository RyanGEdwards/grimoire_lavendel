package com.project.grimoirelavendel.data.classes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.grimoirelavendel.data.interfaces.DictionaryDAO

@Database(
    entities = [
        DictionaryEntry::class,
        Definition::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DictionaryDB: RoomDatabase() {
    abstract fun dictionaryDAO(): DictionaryDAO

    companion object {
        @Volatile
        private var INSTANCE: DictionaryDB? = null

        fun getInstance(context: Context): DictionaryDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DictionaryDB::class.java,
                    "dictionary_db"
                )
                    .createFromAsset("dictionary.db")
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}

package com.project.grimoirelavendel.data.interfaces

import androidx.room.Dao
import androidx.room.Query
import com.project.grimoirelavendel.data.classes.DictionaryEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDAO {
    @Query("""
        SELECT d.* FROM dictionary_entries d
        WHERE d.term LIKE :query || '%' 
           OR d.reading LIKE :query || '%' 
           OR d.id IN (
               SELECT rowid FROM fts_definitions 
               WHERE fts_definitions MATCH :query
           )
        ORDER BY score DESC 
        LIMIT 50
    """)
    fun search(query: String): Flow<List<DictionaryEntry>>
}

package com.project.grimoirelavendel.data.interfaces

import androidx.room.Dao
import androidx.room.Query
import com.project.grimoirelavendel.data.classes.DictionaryEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDAO {
    @Query("""
        SELECT * FROM dictionary_entries 
        WHERE term LIKE :query || '%' OR reading LIKE :query || '%' 
        ORDER BY score DESC 
        LIMIT 50
    """)
    fun search(query: String): Flow<List<DictionaryEntry>>
}

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
        ORDER BY 
            CASE 
                -- 1. Exact term or reading match (Japanese headwords)
                WHEN d.term = :query OR d.reading = :query THEN 1

                -- 2. Term or reading starts with the exact query
                WHEN d.term LIKE :query || '%' OR d.reading LIKE :query || '%' THEN 2

                -- 3. Definition contains the exact standalone query word
                WHEN d.id IN (
                    SELECT rowid FROM fts_definitions 
                    WHERE fts_definitions MATCH '"' || :query || '"'
                ) THEN 3
        
                -- 4. Fallback for broader/partial FTS matches
                ELSE 4
            END ASC,
        -- Secondary sort: frequency score (your original ranking criterion)
        d.score DESC,
        -- Tertiary sort: shorter terms rank higher (e.g. 束 before 束縛)
        LENGTH(d.term) ASC
        LIMIT 50
    """)
    fun search(query: String): Flow<List<DictionaryEntry>>
}

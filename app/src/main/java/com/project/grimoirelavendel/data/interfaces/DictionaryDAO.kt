package com.project.grimoirelavendel.data.interfaces

import androidx.room.Dao
import androidx.room.Query
import com.project.grimoirelavendel.data.classes.DictionaryEntryWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDAO {
    @Query("""
        SELECT d.* 
        FROM dictionary_entries d
        JOIN (
            -- 1. High-priority Japanese B-Tree lookup (Hits idx_term and idx_reading)
            SELECT id, 1 AS match_type 
            FROM dictionary_entries 
            WHERE term = :query OR reading = :query
            
            UNION ALL
            
            -- 2. Japanese Prefix B-Tree lookup
            SELECT id, 2 AS match_type 
            FROM dictionary_entries 
            WHERE (term LIKE :query || '%' OR reading LIKE :query || '%')
                AND term != :query AND reading != :query
            
            UNION ALL
            
            -- 3. Fast English FTS lookup (Hits FTS index directly)
            SELECT rowid AS id, 3 AS match_type 
            FROM fts_definitions 
            WHERE clean_glossary_text LIKE '%' || :query || '%'
        ) AS matches ON d.id = matches.id
        GROUP BY d.id -- Deduplicate entries that match both JP prefix and FTS
        ORDER BY 
            MIN(matches.match_type) ASC,
            d.score DESC,
            LENGTH(d.term) ASC
        LIMIT 50
    """)
    fun search(query: String): Flow<List<DictionaryEntryWithDetails>>
}

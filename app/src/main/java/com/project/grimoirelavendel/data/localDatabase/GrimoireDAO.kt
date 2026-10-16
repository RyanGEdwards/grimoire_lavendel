package com.project.grimoirelavendel.data.localDatabase

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.project.grimoirelavendel.data.localDatabase.dataTypes.SearchResult
import kotlinx.coroutines.flow.Flow

@Dao
interface GrimoireDAO {
    @Transaction
    @Query("""
        SELECT e.* FROM entries e
        JOIN entries_fts f ON e.id = f.entry_id
        WHERE entries_fts MATCH :query
        LIMIT 50
    """)
    fun searchRaw(query: String): Flow<List<SearchResult>>

    fun search(query: String): Flow<List<SearchResult>> {
        val formattedQuery = query.trim()
            .split("\\s+".toRegex())
            .filter { it.isNotEmpty() }
            .joinToString(" ") { "$it*" }

        return searchRaw(formattedQuery)
    }
}

package com.project.grimoirelavendel.data.localDatabase

import com.project.grimoirelavendel.data.localDatabase.dataTypes.SearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn

class GrimoireRepository (private val database: GrimoireDB) {
    suspend fun search (query: String): List<SearchResult> {
        return database.grimoireDAO()
            .search(query)
            .flowOn(Dispatchers.IO)
            .first()
    }
}

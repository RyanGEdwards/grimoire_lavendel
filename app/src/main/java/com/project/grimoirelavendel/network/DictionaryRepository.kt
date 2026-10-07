package com.project.grimoirelavendel.network

import com.project.grimoirelavendel.data.classes.DictionaryDB
import com.project.grimoirelavendel.data.classes.DictionaryEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn

class DictionaryRepository (private val database: DictionaryDB) {
    suspend fun searchDictionary(query: String): List<DictionaryEntry> {
        return database.dictionaryDAO()
            .search(query)
            .flowOn(Dispatchers.IO)
            .first()
    }
}

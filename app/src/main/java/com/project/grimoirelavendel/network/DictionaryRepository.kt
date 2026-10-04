package com.project.grimoirelavendel.network

import com.project.grimoirelavendel.data.classes.DictionaryDB
import com.project.grimoirelavendel.data.classes.DictionaryEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class DictionaryRepository (private val database: DictionaryDB) {
    fun searchDictionary(query: String): Flow<List<DictionaryEntry>> {
        return database.dictionaryDAO().search(query).flowOn(Dispatchers.IO)
    }
}

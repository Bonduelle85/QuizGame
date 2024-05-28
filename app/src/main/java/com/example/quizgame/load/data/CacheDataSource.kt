package com.example.quizgame.load.data

import com.example.quizgame.core.data.StringCache
import com.google.gson.Gson

interface CacheDataSource {

    suspend fun save(data: ResponseCloud)

    fun read(): List<QuestionAndChoicesCloud>

    class Base(
        private val stringCache: StringCache,
        private val gson: Gson
    ) : CacheDataSource {

        override suspend fun save(data: ResponseCloud) {
            stringCache.save(gson.toJson(data))
        }

        override fun read(): List<QuestionAndChoicesCloud> {
            return gson.fromJson(stringCache.read(), ResponseCloud::class.java).items
        }
    }
}
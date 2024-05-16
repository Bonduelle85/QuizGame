package com.example.quizgame.load.data

import com.example.quizgame.core.data.StringCache
import com.example.quizgame.load.presentation.LoadScreen

interface LoadRepository {

    fun load(): LoadResult
    fun saveLastScreenIsProgress()

    class Base(
        private val lastScreen: StringCache,
        private val cloudDataSource: CloudDataSource,
        private val cacheDataSource: CacheDataSource
    ) : LoadRepository {

        override fun load(): LoadResult {
            return try {
                val data = cloudDataSource.data()
                cacheDataSource.save(ResponseCloud(data))
                LoadResult.Success
            } catch (e: Exception) {
                LoadResult.Error(e.message ?: "error")
            }

        }

        override fun saveLastScreenIsProgress() {
            lastScreen.save(LoadScreen::class.java.canonicalName!!)
        }

    }
}

interface LoadResult {

    fun isSuccessful(): Boolean

    fun message(): String

    object Success : LoadResult {

        override fun isSuccessful(): Boolean {
            return true
        }

        override fun message(): String {
            throw IllegalStateException()
        }
    }

    data class Error(private val message: String) : LoadResult {

        override fun isSuccessful(): Boolean {
            return false
        }

        override fun message(): String {
            return message
        }
    }
}
package com.example.quizgame.load.data

interface LoadRepository {

    fun load(): LoadResult


    class Base(
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
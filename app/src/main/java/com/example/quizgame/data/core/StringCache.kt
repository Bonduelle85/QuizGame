package com.example.quizgame.data.core

interface StringCache {

    fun save(value: String)

    fun read(): String

    class Base(
        private val key: String,
        private val permanentStorage: PermanentStorage,
        private val default: String
    ) : StringCache {

        override fun save(value: String) {
            permanentStorage.save(value, key)
        }

        override fun read(): String {
            return permanentStorage.read(key, default)
        }
    }
}
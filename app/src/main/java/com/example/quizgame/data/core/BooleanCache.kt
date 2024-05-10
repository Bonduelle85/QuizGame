package com.example.quizgame.data.core

interface BooleanCache {

    fun save(value: Boolean)

    fun read(): Boolean

    class Base(
        private val key: String,
        private val permanentStorage: PermanentStorage,
        private val default: Boolean
    ) : BooleanCache {

        override fun save(value: Boolean) {
            permanentStorage.save(value, key)
        }

        override fun read(): Boolean {
            return permanentStorage.read(key, default)
        }
    }
}
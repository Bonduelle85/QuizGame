package com.example.quizgame.core.data

import android.content.SharedPreferences

interface PermanentStorage {

    fun save(value: Int, key: String)
    fun save(value: Boolean, key: String)
    fun save(value: String, key: String)

    fun read(key: String, default: Int): Int
    fun read(key: String, default: Boolean): Boolean
    fun read(key: String, default: String): String

    class Base(private val sharedPreferences: SharedPreferences) : PermanentStorage {

        override fun save(value: Int, key: String) {
            sharedPreferences.edit()
                .putInt(key, value)
                .apply()
        }

        override fun save(value: Boolean, key: String) {
            sharedPreferences.edit().putBoolean(key, value).apply()
        }

        override fun save(value: String, key: String) {
            sharedPreferences.edit().putString(key, value).apply()
        }

        override fun read(key: String, default: Int): Int {
            return sharedPreferences.getInt(key, default)
        }

        override fun read(key: String, default: Boolean): Boolean {
            return sharedPreferences.getBoolean(key, default)
        }

        override fun read(key: String, default: String): String {
            return sharedPreferences.getString(key, default) ?: default
        }
    }
}
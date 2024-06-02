package com.example.quizgame.load.data.cache

import android.content.Context
import androidx.room.Room

interface CacheModule {

    fun database(): QuizDatabase

    class Base(context: Context) : CacheModule {

        private val database: QuizDatabase by lazy {
            Room.databaseBuilder(
                context,
                QuizDatabase::class.java,
                QuizDatabase::class.java.simpleName
            ).build()
        }

        override fun database(): QuizDatabase {
            return database
        }
    }
}
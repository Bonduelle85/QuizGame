package com.example.quizgame.load.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuestionCache::class], exportSchema = false, version = 1)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun dao(): QuestionAndChoicesDao
}
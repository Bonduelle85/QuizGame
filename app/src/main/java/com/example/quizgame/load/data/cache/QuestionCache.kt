package com.example.quizgame.load.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class QuestionCache(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("question")
    val question: String,
    @ColumnInfo("correct")
    val correct: String,
    @ColumnInfo("incorrect_one")
    val incorrectOne: String,
    @ColumnInfo("incorrect_two")
    val incorrectTwo: String,
    @ColumnInfo("incorrect_three")
    val incorrectThree: String
)
package com.example.quizgame.load.data

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

interface CloudDataSource {

    fun data(): List<QuestionAndChoicesCloud>

    class Base(
        private val service: QuestionsService,
        private val gson: Gson
    ) : CloudDataSource {

        override fun data(): List<QuestionAndChoicesCloud> {
            val response: String = service.data()
            return gson.fromJson(response, ResponseCloud::class.java).items
        }
    }
}

data class ResponseCloud(
    @SerializedName("results")
    val items: List<QuestionAndChoicesCloud>
)

data class QuestionAndChoicesCloud(

    @SerializedName("question")
    val question: String,
    @SerializedName("correct_answer")
    val correct: String,
    @SerializedName("incorrect_answers")
    val incorrects: List<String>
)
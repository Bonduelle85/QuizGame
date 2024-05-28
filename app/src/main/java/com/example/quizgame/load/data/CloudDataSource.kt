package com.example.quizgame.load.data

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import java.net.UnknownHostException

interface CloudDataSource {

    fun data(): List<QuestionAndChoicesCloud>

    class Base(private val questionService: QuestionService) : CloudDataSource {

        override fun data(): List<QuestionAndChoicesCloud> {
            try {
                val data: Call<ResponseCloud> = questionService.data()
                return data.execute().body()!!.items
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    throw IllegalStateException("No internet connection")
                else
                    throw IllegalStateException("unknown error")
            }
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
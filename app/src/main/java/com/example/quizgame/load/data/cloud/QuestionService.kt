package com.example.quizgame.load.data.cloud

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.UnknownHostException

interface QuestionService {

    @GET("api.php")
    fun data(
        @Query("amount") count: Int,
        @Query("difficulty") difficulty: String = "easy",
        @Query("type") type: String = "multiple"
    ): Call<ResponseCloud>

}

class FakeService : QuestionService {

    private val response = ResponseCloud(
        listOf(
            QuestionAndChoicesCloud(
                "What color is the sky?",
                "blue",
                listOf("green", "yellow", "red")
            ),
            QuestionAndChoicesCloud(
                "What color is grass?",
                "green",
                listOf("blue", "yellow", "red")
            ),
            QuestionAndChoicesCloud(
                "What color is sun?",
                "yellow",
                listOf("blue", "green", "red")
            ),
        )
    )

    private var showSuccess = false

    override fun data(count: Int, difficulty: String, type: String): Call<ResponseCloud> {
        Thread.sleep(1000)
        if (showSuccess)
            return object : Call<ResponseCloud> {
                override fun clone(): Call<ResponseCloud> {
                    return this
                }

                override fun execute(): Response<ResponseCloud> {
                    return Response.success(response)
                }

                override fun isExecuted(): Boolean {
                    return false
                }

                override fun cancel() {
                }

                override fun isCanceled(): Boolean {
                    return false
                }

                override fun request(): Request {
                    TODO("Not yet implemented")
                }

                override fun timeout(): Timeout {
                    TODO("Not yet implemented")
                }

                override fun enqueue(callback: Callback<ResponseCloud>) {
                    TODO("Not yet implemented")
                }

            }
        else {
            showSuccess = true
            throw UnknownHostException()
        }
    }
}
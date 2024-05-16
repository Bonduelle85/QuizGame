package com.example.quizgame.load.data

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

interface QuestionsService {

    fun data(): String

    class Base(
        private val count: Int = 3
    ) : QuestionsService {

        override fun data(): String {
            val urlGetRequest =
                URL("https://opentdb.com/api.php?amount=$count&difficulty=easy&type=multiple")
            val apiConnexion = urlGetRequest.openConnection() as HttpURLConnection
            apiConnexion.requestMethod = "GET"

            try {
                val responseCode = apiConnexion.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val `in` = BufferedReader(InputStreamReader(apiConnexion.inputStream))
                    val response = StringBuffer()
                    var inputLine: String?
                    while (`in`.readLine().also { inputLine = it } != null) {
                        response.append(inputLine)
                    }
                    `in`.close()

                    return response.toString()
                } else {
                    throw IllegalStateException("unknown error")
                }
            } finally {
                apiConnexion.disconnect()
            }
        }
    }

    class Mock(
        private val gson: Gson
    ) : QuestionsService {

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

        override fun data(): String {
            Thread.sleep(1000)
            if (showSuccess)
                return gson.toJson(response)
            else {
                showSuccess = true
                throw IllegalStateException("No internet connection")
            }
        }
    }
}
package com.example.quizgame.data

interface MainRepository {

    fun lastScreenIsGame(): Boolean

    class Base(private val lastScreenIsGame: BooleanCache) : MainRepository {

        override fun lastScreenIsGame(): Boolean {
            return lastScreenIsGame.read()
        }
    }
}
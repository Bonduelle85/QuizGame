package com.example.quizgame

interface Repository {

    fun questionAndChoices(): QuestionAndChoices
    fun chooseFirst()
    fun chooseSecond()
    fun chooseThird()
    fun chooseFourth()
    fun check(): CheckResult
    fun next()

    class Base : Repository {
        override fun questionAndChoices(): QuestionAndChoices {
            TODO("Not yet implemented")
        }

        override fun chooseFirst() {
            TODO("Not yet implemented")
        }

        override fun chooseSecond() {
            TODO("Not yet implemented")
        }

        override fun chooseThird() {
            TODO("Not yet implemented")
        }

        override fun chooseFourth() {
            TODO("Not yet implemented")
        }

        override fun check(): CheckResult {
            TODO("Not yet implemented")
        }

        override fun next() {
            TODO("Not yet implemented")
        }
    }
}
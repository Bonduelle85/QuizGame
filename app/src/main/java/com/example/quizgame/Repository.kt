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

        private val questionAndChoicesList = listOf(
            QuestionAndChoices(
                question = "What color is the sky?",
                choiceOne = "blue",
                choiceTwo = "green",
                choiceThree = "yellow",
                choiceFour = "red",
            ),
            QuestionAndChoices(
                question = "What color is grass?",
                choiceOne = "blue",
                choiceTwo = "green",
                choiceThree = "yellow",
                choiceFour = "red",
            ),
            QuestionAndChoices(
                question = "What color is sun?",
                choiceOne = "blue",
                choiceTwo = "green",
                choiceThree = "yellow",
                choiceFour = "red",
            ),
        )

        private var currentIndex = 0
        private var userChoiceIndex = -1

        private val answersList = listOf(
            0,
            1,
            2
        )
        override fun questionAndChoices(): QuestionAndChoices {
            return questionAndChoicesList[currentIndex]
        }

        override fun chooseFirst() {
            userChoiceIndex = 0
        }

        override fun chooseSecond() {
            userChoiceIndex = 1
        }

        override fun chooseThird() {
            userChoiceIndex = 2
        }

        override fun chooseFourth() {
            userChoiceIndex = 3
        }

        override fun check(): CheckResult {
            val correctIndex = answersList[currentIndex]
            return if (userChoiceIndex == correctIndex)
                CheckResult.Correct(correctIndex = correctIndex)
            else
                CheckResult.Incorrect(correctIndex = correctIndex, incorrectIndex = userChoiceIndex)
        }

        override fun next() {
            currentIndex++
            if (currentIndex == questionAndChoicesList.size) currentIndex = 0
            userChoiceIndex = -1
        }
    }
}
package com.example.quizgame

interface Repository {

    fun questionAndChoices(): QuestionAndChoices
    fun chooseFirst()
    fun chooseSecond()
    fun chooseThird()
    fun chooseFourth()
    fun check(): CheckResult
    fun next()

    class Base(
        private val currentIndex: IntCache,
        private val userChoiceIndex: IntCache,
    ) : Repository {

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

        private val answersList = listOf(
            0,
            1,
            2
        )

        override fun questionAndChoices(): QuestionAndChoices {
            return questionAndChoicesList[currentIndex.read()]
        }

        override fun chooseFirst() {
            userChoiceIndex.save(0)
        }

        override fun chooseSecond() {
            userChoiceIndex.save(1)
        }

        override fun chooseThird() {
            userChoiceIndex.save(2)
        }

        override fun chooseFourth() {
            userChoiceIndex.save(3)
        }

        override fun check(): CheckResult {
            val correctIndex = answersList[currentIndex.read()]
            return if (userChoiceIndex.read() == correctIndex)
                CheckResult.Correct(correctIndex = correctIndex)
            else
                CheckResult.Incorrect(
                    correctIndex = correctIndex,
                    incorrectIndex = userChoiceIndex.read()
                )
        }

        override fun next() {
            val current = currentIndex.read()
            val new = current + 1
            currentIndex.save(new)

            if (currentIndex.read() == questionAndChoicesList.size)
                currentIndex.save(0)
            userChoiceIndex.save(-1)
        }
    }
}
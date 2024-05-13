package com.example.quizgame.game.data

import com.example.quizgame.core.data.CheckResult
import com.example.quizgame.core.data.IntCache
import com.example.quizgame.core.data.QuestionAndChoices
import com.example.quizgame.core.data.StringCache
import com.example.quizgame.game.presentation.GameScreen

interface GameRepository {

    fun questionAndChoices(): QuestionAndChoices
    fun chooseFirst()
    fun chooseSecond()
    fun chooseThird()
    fun chooseFourth()
    fun check(): CheckResult
    fun next()
    fun noMoreQuestions(): Boolean
    fun saveCurrentScreenIsGame()

    class Base(
        private val lastScreen: StringCache,
        private val corrects: IntCache,
        private val incorrects: IntCache,
        private val currentIndex: IntCache,
        private val userChoiceIndex: IntCache,
    ) : GameRepository {

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
            return if (userChoiceIndex.read() == correctIndex) {
                corrects.save(corrects.read() + 1)
                CheckResult.Correct(correctIndex = correctIndex)
            } else {
                incorrects.save(incorrects.read() + 1)
                CheckResult.Incorrect(
                    correctIndex = correctIndex,
                    incorrectIndex = userChoiceIndex.read()
                )
            }
        }

        override fun next() {
            val current = currentIndex.read()
            val new = current + 1
            currentIndex.save(new)

            userChoiceIndex.save(-1)
        }

        override fun noMoreQuestions(): Boolean {
            val noMore = currentIndex.read() == questionAndChoicesList.size
            if (noMore)
                currentIndex.save(0)
            return noMore
        }

        override fun saveCurrentScreenIsGame() {
            lastScreen.save(GameScreen::class.java.canonicalName)
        }
    }
}
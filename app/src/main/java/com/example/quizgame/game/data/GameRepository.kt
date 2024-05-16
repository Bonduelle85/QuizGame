package com.example.quizgame.game.data

import com.example.quizgame.core.data.CheckResult
import com.example.quizgame.core.data.IntCache
import com.example.quizgame.core.data.QuestionAndChoices
import com.example.quizgame.core.data.StringCache
import com.example.quizgame.game.presentation.GameScreen
import com.example.quizgame.load.data.CacheDataSource

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
        cacheDataSource: CacheDataSource,
    ) : GameRepository {

        private val questionAndChoicesList = cacheDataSource.read()

        override fun questionAndChoices(): QuestionAndChoices {
            val current = questionAndChoicesList[currentIndex.read()]
            val list = (current.incorrects + current.correct)//todo
            return QuestionAndChoices(current.question, list[0], list[1], list[2], list[3])
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
            val correctIndex = 3
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
package com.example.quizgame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * TestCase N1: Correct
     *
     * 1) Нажать на правильный вариант
     *      выбор сделан (фиолетовый цвет)
     * 2) Нажать “проверить”
     *      верный вариант зеленый,
     *      остальные - серые (не кликабельны)
     * 3) Нажать "Дальше"
     *      отображается следующий вопрос,
     *      кнопки вариантов ответа желтые (активные),
     *      кнопка "проверить" не видна
     */
    @Test
    fun caseNumberOne() {
        var quizPage = QuizPage(
            question = "What color is the sky?",
            choice1 = "blue",
            choice2 = "green",
            choice3 = "yellow",
            choice4 = "red"
        )
        quizPage.checkStateIsQuestion()

        quizPage.clickChoiceOne()
        quizPage.checkStateIsFirstChoiceMade()

        quizPage.clickCheckButton()
        quizPage.checkCorrectStateFirst()

        quizPage.clickChoiceOne()
        quizPage.clickChoiceTwo()
        quizPage.clickChoiceThree()
        quizPage.clickChoiceFour()
        quizPage.checkCorrectStateFirst()

        quizPage.clickNext()
        quizPage = QuizPage(
            question = "What color is grass?",
            choice1 = "blue",
            choice2 = "green",
            choice3 = "yellow",
            choice4 = "red"
        )
        quizPage.checkStateIsQuestion()
    }

    /**
     * TestCase N2: Incorrect
     *
     * 1) Нажать на неправильный вариант ответа
     *      выбор сделан (фиолетовый цвет)
     * 2) нажать кнопку “проверить”
     *      верный вариант зеленый,
     *      выбранный красный,
     *      остальные - серый
     * 3) Нажать кнопку "дальше"
     *      отображается следующий вопрос,
     *      кнопки желтые,
     *      кнопка "проверить" не видна
     */
    @Test
    fun caseNumberTwo() {
        var quizPage = QuizPage(
            question = "What color is the sky?",
            choice1 = "blue",
            choice2 = "green",
            choice3 = "yellow",
            choice4 = "red"
        )
        quizPage.checkStateIsQuestion()

        quizPage.clickChoiceTwo() // "green - Incorrect"
        quizPage.checkStateIsSecondChoiceMade()

        quizPage.clickCheckButton()
        quizPage.checkIncorrectState(choice = 1, correct = 0) // выбранный - красный, правильный - зелёный

        quizPage.clickChoiceOne()
        quizPage.clickChoiceTwo()
        quizPage.clickChoiceThree()
        quizPage.clickChoiceFour()
        quizPage.checkIncorrectState(choice = 1, correct = 0) // состояние не изменилось

        quizPage.clickNext()
        quizPage = QuizPage(
            question = "What color is grass?",
            choice1 = "blue",
            choice2 = "green",
            choice3 = "yellow",
            choice4 = "red"
        )
        quizPage.checkStateIsQuestion()
    }

    /**
     * TestCase N3: Double choice
     * 1) Нажать на первый вариант
     *      первый вариант фиолетовый
     * 2) Нажать на второй вариант
     *      второй вариант фиолетовый, а первый желтый
     * 3) Нажать на третий вариант
     *      третий вариант фиолетовый, а второй желтый
     * 4) Нажать на четвертый вариант
     *      четвертый вариант фиолетовый, а третий желтый
     */
    @Test
    fun caseNumberThree() {
        var quizPage = QuizPage(
            question = "What color is the sky?",
            choice1 = "blue",
            choice2 = "green",
            choice3 = "yellow",
            choice4 = "red"
        )
        quizPage.checkStateIsQuestion()

        quizPage.clickChoiceOne()
        quizPage.checkStateIsFirstChoiceMade()

        quizPage.clickChoiceTwo()
        quizPage.checkStateIsSecondChoiceMade()

        quizPage.clickChoiceThree()
        quizPage.checkStateIsThirdChoiceMade()

        quizPage.clickChoiceFour()
        quizPage.checkStateIsFourthChoiceMade()
    }
}
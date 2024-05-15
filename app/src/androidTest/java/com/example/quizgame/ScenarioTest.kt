package com.example.quizgame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.quizgame.main.presentation.MainActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var quizPage: QuizPage

    @Before
    fun setup() {
        quizPage = QuizPage(
            question = "What color is the sky?",
            choice1 = "blue",
            choice2 = "green",
            choice3 = "yellow",
            choice4 = "red"
        )
    }

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
        caseNumberFour()

        quizPage.clickChoiceOne()
        quizPage.checkStateIsFirstChoiceMade()
        activityScenarioRule.scenario.recreate()
        quizPage.checkStateIsFirstChoiceMade()

        quizPage.clickCheckButton()
        quizPage.checkCorrectStateFirst()
        activityScenarioRule.scenario.recreate()
        quizPage.checkStateIsFirstChoiceMade()

        quizPage.clickChoiceOne()
        quizPage.clickChoiceTwo()
        quizPage.clickChoiceThree()
        quizPage.clickChoiceFour()
        quizPage.checkCorrectStateFirst()
        activityScenarioRule.scenario.recreate()
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
        activityScenarioRule.scenario.recreate()
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
        caseNumberFour()

        quizPage.clickChoiceTwo()
        quizPage.checkStateIsSecondChoiceMade()

        quizPage.clickCheckButton()
        quizPage.checkIncorrectState(choice = 1, correct = 0)

        quizPage.clickChoiceOne()
        quizPage.clickChoiceTwo()
        quizPage.clickChoiceThree()
        quizPage.clickChoiceFour()
        quizPage.checkIncorrectState(choice = 1, correct = 0)

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
        caseNumberFour()

        quizPage.clickChoiceOne()
        quizPage.checkStateIsFirstChoiceMade()

        quizPage.clickChoiceTwo()
        quizPage.checkStateIsSecondChoiceMade()

        quizPage.clickChoiceThree()
        quizPage.checkStateIsThirdChoiceMade()

        quizPage.clickChoiceFour()
        quizPage.checkStateIsFourthChoiceMade()
    }

    /**
     * TestCase 4
     * 1) progress -> error
     * 2) click retry
     * 3) progress -> success
     */
    @Test
    fun caseNumberFour() {
        val loadPage = LoadPage()
        loadPage.checkProgressState()

        loadPage.waitUntilError()
        loadPage.checkErrorState(message = "No internet connection")

        loadPage.clickRetry()
        loadPage.checkProgressState()

        loadPage.waitUntilDisappear()
        quizPage.checkStateIsQuestion()
    }
}
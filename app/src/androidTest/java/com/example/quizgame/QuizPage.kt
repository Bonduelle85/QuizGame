package com.example.quizgame

import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withParent


class QuizPage(
    question: String,
    choice1: String,
    choice2: String,
    choice3: String,
    choice4: String
) {

    private val rootId: Int = R.id.rootLayout
    private val parent = withParent(isAssignableFrom(LinearLayout::class.java))
    private val question = QuestionUi(rootId, parent, question)
    private val choiceOne = ChoiceUi(R.id.choiceOneButton, choice1, rootId, parent)
    private val choiceTwo = ChoiceUi(R.id.choiceTwoButton, choice2, rootId, parent)
    private val choiceThree = ChoiceUi(R.id.choiceThreeButton, choice3, rootId, parent)
    private val choiceFour = ChoiceUi(R.id.choiceFourButton, choice4, rootId, parent)
    private val actionButton = ActionButtonUi(parent, rootId)

    fun checkStateIsQuestion() {
        question.checkQuestionState()

        choiceOne.checkQuestionState()
        choiceTwo.checkQuestionState()
        choiceThree.checkQuestionState()
        choiceFour.checkQuestionState()

        actionButton.checkQuestionState()
    }

    fun checkStateIsFirstChoiceMade() {
        choiceOne.checkChoiceMade()
        choiceTwo.checkChoiceNotMade()
        choiceThree.checkChoiceNotMade()
        choiceFour.checkChoiceNotMade()

        actionButton.checkStateIsCheck()
    }
    fun checkStateIsSecondChoiceMade() {
        choiceOne.checkChoiceNotMade()
        choiceTwo.checkChoiceMade()
        choiceThree.checkChoiceNotMade()
        choiceFour.checkChoiceNotMade()

        actionButton.checkStateIsCheck()
    }
    fun checkStateIsThirdChoiceMade() {
        choiceOne.checkChoiceNotMade()
        choiceTwo.checkChoiceNotMade()
        choiceThree.checkChoiceMade()
        choiceFour.checkChoiceNotMade()

        actionButton.checkStateIsCheck()
    }
    fun checkStateIsFourthChoiceMade() {
        choiceOne.checkChoiceNotMade()
        choiceTwo.checkChoiceNotMade()
        choiceThree.checkChoiceNotMade()
        choiceFour.checkChoiceMade()

        actionButton.checkStateIsCheck()
    }
    fun checkCorrectStateFirst() {
        choiceOne.checkCorrect()
        choiceTwo.checkInactive()
        choiceThree.checkInactive()
        choiceFour.checkInactive()
        actionButton.checkStateIsNext()
    }
    fun checkCorrectStateSecond() {
        choiceOne.checkInactive()
        choiceTwo.checkCorrect()
        choiceThree.checkInactive()
        choiceFour.checkInactive()
        actionButton.checkStateIsNext()
    }
    fun checkCorrectStateThird() {
        choiceOne.checkInactive()
        choiceTwo.checkInactive()
        choiceThree.checkCorrect()
        choiceFour.checkInactive()
        actionButton.checkStateIsNext()
    }
    fun checkCorrectStateFourth() {
        choiceOne.checkInactive()
        choiceTwo.checkInactive()
        choiceThree.checkInactive()
        choiceFour.checkCorrect()
        actionButton.checkStateIsNext()
    }
    fun checkIncorrectState(choice: Int, correct: Int) {
        choiceOne.checkIncorrectState(correct, choice, 0)
        choiceTwo.checkIncorrectState(correct, choice, 1)
        choiceThree.checkIncorrectState(correct, choice, 2)
        choiceFour.checkIncorrectState(correct, choice, 3)

        actionButton.checkStateIsNext()
    }

    fun clickCheckButton() {
        actionButton.click()
    }

    fun clickChoiceOne() {
        choiceOne.click()
    }

    fun clickChoiceTwo() {
        choiceTwo.click()
    }

    fun clickChoiceThree() {
        choiceThree.click()
    }

    fun clickChoiceFour() {
        choiceFour.click()
    }

    fun clickNext() {
        actionButton.click()
    }
}

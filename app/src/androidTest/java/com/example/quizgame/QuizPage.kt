package com.example.quizgame

import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not


class QuizPage(
    private val question: String,
    choice1: String,
    choice2: String,
    choice3: String,
    choice4: String
) {

    private val rootId: Int = R.id.rootLayout
    private val choiceOneButtonId: Int = R.id.choiceOneButton
    private val choiceTwoButtonId: Int = R.id.choiceTwoButton
    private val choiceThreeButtonId: Int = R.id.choiceThreeButton
    private val choiceFourButtonId: Int = R.id.choiceFourButton

    private val actionButtonId: Int = R.id.actionButton

    private val parent = withParent(isAssignableFrom(LinearLayout::class.java))

    private val buttonOne = ChoiceUi(choiceOneButtonId, choice1, rootId, parent)
    private val buttonTwo = ChoiceUi(choiceTwoButtonId, choice2, rootId, parent)
    private val buttonThree = ChoiceUi(choiceThreeButtonId, choice3, rootId, parent)
    private val buttonFour = ChoiceUi(choiceFourButtonId, choice4, rootId, parent)

    fun checkStateIsQuestion() {
        onView(
            allOf(
                withId(R.id.questionTextView),
                withText(question),
                isAssignableFrom(TextView::class.java),
                parent,
                withParent(withId(rootId)),
            )
        ).check(matches(isDisplayed()))

        buttonOne.checkQuestionState()
        buttonTwo.checkQuestionState()
        buttonThree.checkQuestionState()
        buttonFour.checkQuestionState()

        onView(
            allOf(
                withId(actionButtonId),
                isAssignableFrom(Button::class.java),
                parent,
                withParent(withId(rootId)),
            )
        ).check(matches(not(isDisplayed())))
    }

    fun checkStateIsFirstChoiceMade() {
        buttonOne.checkChoiceMade()
        buttonTwo.checkChoiceNotMade()
        buttonThree.checkChoiceNotMade()
        buttonFour.checkChoiceNotMade()
        onView(withId(actionButtonId)).check(matches(withText(R.string.check)))
            .check(matches(isDisplayed()))
    }
    fun checkStateIsSecondChoiceMade() {
        buttonOne.checkChoiceNotMade()
        buttonTwo.checkChoiceMade()
        buttonThree.checkChoiceNotMade()
        buttonFour.checkChoiceNotMade()
        onView(withId(actionButtonId)).check(matches(withText(R.string.check)))
            .check(matches(isDisplayed()))
    }
    fun checkStateIsThirdChoiceMade() {
        buttonOne.checkChoiceNotMade()
        buttonTwo.checkChoiceNotMade()
        buttonThree.checkChoiceMade()
        buttonFour.checkChoiceNotMade()
        onView(withId(actionButtonId)).check(matches(withText(R.string.check)))
            .check(matches(isDisplayed()))
    }
    fun checkStateIsFourthChoiceMade() {
        buttonOne.checkChoiceNotMade()
        buttonTwo.checkChoiceNotMade()
        buttonThree.checkChoiceNotMade()
        buttonFour.checkChoiceMade()
        onView(withId(actionButtonId)).check(matches(withText(R.string.check)))
            .check(matches(isDisplayed()))
    }
    fun checkCorrectStateFirst() {
        buttonOne.checkCorrect()
        buttonTwo.checkInactive()
        buttonThree.checkInactive()
        buttonFour.checkInactive()
        onView(withId(actionButtonId)).check(matches(withText(R.string.next)))
    }
    fun checkCorrectStateSecond() {
        buttonOne.checkInactive()
        buttonTwo.checkCorrect()
        buttonThree.checkInactive()
        buttonFour.checkInactive()
        onView(withId(actionButtonId)).check(matches(withText(R.string.next)))
    }
    fun checkCorrectStateThird() {
        buttonOne.checkInactive()
        buttonTwo.checkInactive()
        buttonThree.checkCorrect()
        buttonFour.checkInactive()
        onView(withId(actionButtonId)).check(matches(withText(R.string.next)))
    }
    fun checkCorrectStateFourth() {
        buttonOne.checkInactive()
        buttonTwo.checkInactive()
        buttonThree.checkInactive()
        buttonFour.checkCorrect()
        onView(withId(actionButtonId)).check(matches(withText(R.string.next)))
    }
    fun checkIncorrectState(choice: Int, correct: Int) {
        buttonOne.checkIncorrectState(correct, choice, 0)
        buttonTwo.checkIncorrectState(correct, choice, 1)
        buttonThree.checkIncorrectState(correct, choice, 2)
        buttonFour.checkIncorrectState(correct, choice, 3)

        onView(withId(actionButtonId)).check(matches(withText(R.string.next)))
    }

    fun clickCheckButton() {
        onView(withId(actionButtonId)).perform(click())
    }

    fun clickChoiceOne() {
        onView(withId(choiceOneButtonId)).perform(click())
    }

    fun clickChoiceTwo() {
        onView(withId(choiceTwoButtonId)).perform(click())
    }

    fun clickChoiceThree() {
        onView(withId(choiceThreeButtonId)).perform(click())
    }

    fun clickChoiceFour() {
        onView(withId(choiceFourButtonId)).perform(click())
    }

    fun clickNext() {
        onView(withId(actionButtonId)).perform(click())
    }
}

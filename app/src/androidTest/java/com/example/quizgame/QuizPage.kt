package com.example.quizgame

import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isClickable
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

    private val buttonColorQuestionState = "#CAC672"
    private val buttonColorChoiceMadeState = "#C431BE"
    private val buttonColorCorrect = "#3AE355"
    private val buttonColorIncorrect = "#F03131"
    private val buttonColorInactiveState = "#9C9C90"

    private val parent = withParent(isAssignableFrom(LinearLayout::class.java))
    private val buttonClass = isAssignableFrom(Button::class.java)

    private val buttonOne = onView(
        allOf(
            withId(choiceOneButtonId),
            withText(choice1),
            buttonClass,
            parent,
            withParent(withId(rootId)),
        )
    )

    private val buttonTwo = onView(
        allOf(
            withId(choiceTwoButtonId),
            withText(choice2),
            buttonClass,
            parent,
            withParent(withId(rootId)),
        )
    )

    private val buttonThree = onView(
        allOf(
            withId(choiceThreeButtonId),
            withText(choice3),
            buttonClass,
            parent,
            withParent(withId(rootId)),
        )
    )

    private val buttonFour = onView(
        allOf(
            withId(choiceFourButtonId),
            withText(choice4),
            buttonClass,
            parent,
            withParent(withId(rootId)),
        )
    )

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

        buttonOne.check(matches(isClickable()))
            .check(matches(ButtonColorMatcher(buttonColorQuestionState)))

        buttonTwo.check(matches(isClickable()))
            .check(matches(ButtonColorMatcher(buttonColorQuestionState)))

        buttonThree.check(matches(isClickable()))
            .check(matches(ButtonColorMatcher(buttonColorQuestionState)))

        buttonFour.check(matches(isClickable()))
            .check(matches(ButtonColorMatcher(buttonColorQuestionState)))

        onView(
            allOf(
                withId(actionButtonId),
                buttonClass,
                parent,
                withParent(withId(rootId)),
            )
        ).check(matches(not(isDisplayed())))
    }

    fun checkStateIsChoiceMade(choice: Int) {

        val isFirstChoiceMade = choice == 0
        val checkFirstClickableOrNot = if (isFirstChoiceMade)
            matches(not(isClickable()))
        else
            matches(isClickable())
        val colorOne = if (isFirstChoiceMade)
            buttonColorChoiceMadeState
        else
            buttonColorQuestionState
        buttonOne.check(checkFirstClickableOrNot)
            .check(matches(ButtonColorMatcher(colorOne)))

        val isSecondChoiceMade = choice == 1
        val checkSecondClickableOrNot = if (isSecondChoiceMade)
            matches(not(isClickable()))
        else
            matches(isClickable())
        val colorTwo = if (isSecondChoiceMade)
            buttonColorChoiceMadeState
        else
            buttonColorQuestionState
        buttonTwo.check(checkSecondClickableOrNot)
            .check(matches(ButtonColorMatcher(colorTwo)))

        val isThirdChoiceMade = choice == 2
        val checkThirdClickableOrNot = if (isThirdChoiceMade)
            matches(not(isClickable()))
        else
            matches(isClickable())
        val colorThree = if (isThirdChoiceMade)
            buttonColorChoiceMadeState
        else
            buttonColorQuestionState
        buttonThree.check(checkThirdClickableOrNot)
            .check(matches(ButtonColorMatcher(colorThree)))

        val isFourthChoiceMade = choice == 3
        val checkFourthClickableOrNot = if (isFourthChoiceMade)
            matches(not(isClickable()))
        else
            matches(isClickable())
        val colorFour = if (isFourthChoiceMade)
            buttonColorChoiceMadeState
        else
            buttonColorQuestionState
        buttonFour.check(checkFourthClickableOrNot)
            .check(matches(ButtonColorMatcher(colorFour)))

        onView(withId(actionButtonId)).check(matches(withText(R.string.check)))
            .check(matches(isDisplayed()))
    }

    fun checkCorrectState(choice: Int) {
        val isCorrectOne = choice == 0
        val colorOne = if (isCorrectOne) buttonColorCorrect else buttonColorInactiveState
        buttonOne.check(matches(ButtonColorMatcher(colorOne)))
            .check(matches(not(isClickable())))

        val isCorrectTwo = choice == 1
        val colorTwo = if (isCorrectTwo) buttonColorCorrect else buttonColorInactiveState
        buttonOne.check(matches(ButtonColorMatcher(colorTwo)))
            .check(matches(not(isClickable())))

        val isCorrectThree = choice == 2
        val colorThree = if (isCorrectThree) buttonColorCorrect else buttonColorInactiveState
        buttonOne.check(matches(ButtonColorMatcher(colorThree)))
            .check(matches(not(isClickable())))

        val isCorrectFour = choice == 3
        val colorFour = if (isCorrectFour) buttonColorCorrect else buttonColorInactiveState
        buttonOne.check(matches(ButtonColorMatcher(colorFour)))
            .check(matches(not(isClickable())))

        onView(withId(actionButtonId)).check(matches(withText(R.string.next)))
    }

    fun checkIncorrectState(choice: Int, correct: Int) {
        val isCorrectOne = correct == 0
        val colorOne = if (isCorrectOne) buttonColorCorrect else if (choice == 0)
            buttonColorIncorrect
        else
            buttonColorInactiveState
        buttonOne.check(matches(ButtonColorMatcher(colorOne)))
            .check(matches(not(isClickable())))

        val isCorrectTwo = correct == 1
        val colorTwo = if (isCorrectTwo) buttonColorCorrect else if (choice == 1)
            buttonColorIncorrect
        else
            buttonColorInactiveState
        buttonOne.check(matches(ButtonColorMatcher(colorTwo)))
            .check(matches(not(isClickable())))

        val isCorrectThree = correct == 2
        val colorThree = if (isCorrectThree) buttonColorCorrect else if (choice == 2)
            buttonColorIncorrect
        else
            buttonColorInactiveState
        buttonOne.check(matches(ButtonColorMatcher(colorThree)))
            .check(matches(not(isClickable())))

        val isCorrectFour = correct == 3
        val colorFour = if (isCorrectFour) buttonColorCorrect else if (choice == 3)
            buttonColorIncorrect
        else
            buttonColorInactiveState
        buttonOne.check(matches(ButtonColorMatcher(colorFour)))
            .check(matches(not(isClickable())))

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

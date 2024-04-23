package com.example.quizgame

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class ChoiceUi(
    id: Int,
    text: String,
    rootId: Int,
    parent: Matcher<View>
) {

    private val interaction = Espresso.onView(
        Matchers.allOf(
            ViewMatchers.withId(id),
            ViewMatchers.withText(text),
            ViewMatchers.isAssignableFrom(AppCompatButton::class.java),
            parent,
            ViewMatchers.withParent(ViewMatchers.withId(rootId)),
        )
    )

    private val buttonColorQuestionState = "#CAC672"
    private val buttonColorChoiceMadeState = "#C431BE"
    private val buttonColorCorrect = "#3AE355"
    private val buttonColorIncorrect = "#F03131"
    private val buttonColorInactiveState = "#9C9C90"

    fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }

    fun checkQuestionState() {
        interaction.check(ViewAssertions.matches(ViewMatchers.isClickable()))
            .check(ViewAssertions.matches(ButtonColorMatcher(buttonColorQuestionState)))
    }

    fun checkChoiceMade() {
        val checkFirstClickableOrNot =
            ViewAssertions.matches(Matchers.not(ViewMatchers.isClickable()))
        val colorOne = buttonColorChoiceMadeState
        interaction.check(checkFirstClickableOrNot).check(
            ViewAssertions.matches(
                ButtonColorMatcher(
                    colorOne
                )
            )
        )
    }

    fun checkChoiceNotMade() {
        val checkFirstClickableOrNot = ViewAssertions.matches(ViewMatchers.isClickable())
        val colorOne = buttonColorQuestionState
        interaction.check(checkFirstClickableOrNot).check(
            ViewAssertions.matches(
                ButtonColorMatcher(
                    colorOne
                )
            )
        )
    }

    fun checkCorrect() {
        interaction.check(ViewAssertions.matches(ButtonColorMatcher(buttonColorCorrect)))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isClickable())))
    }

    fun checkIncorrect() {
        interaction.check(ViewAssertions.matches(ButtonColorMatcher(buttonColorIncorrect)))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isClickable())))
    }

    fun checkInactive() {
        interaction.check(ViewAssertions.matches(ButtonColorMatcher(buttonColorInactiveState)))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isClickable())))
    }

    fun checkIncorrectState(correct: Int, choice: Int, selfIndex: Int) {
        if (correct == selfIndex)
            checkCorrect()
        else if (choice == selfIndex)
            checkIncorrect()
        else
            checkInactive()
    }
}
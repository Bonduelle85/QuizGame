package com.example.quizgame.ui

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.quizgame.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class ActionButtonUi(
    parent: Matcher<View>,
    rootId: Int
) {

    private val actionButtonId: Int = R.id.actionButton
    private val interaction = Espresso.onView(
        Matchers.allOf(
            ViewMatchers.withId(actionButtonId),
            ViewMatchers.isAssignableFrom(AppCompatButton::class.java),
            parent,
            ViewMatchers.withParent(ViewMatchers.withId(rootId)),
        )
    )

    fun checkQuestionState() {
        interaction.check(matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    fun checkStateIsCheck() {
        interaction
            .check(matches(withText(R.string.check)))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    fun checkStateIsNext() {
        interaction.check(matches(withText(R.string.next)))
    }

    fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }
}
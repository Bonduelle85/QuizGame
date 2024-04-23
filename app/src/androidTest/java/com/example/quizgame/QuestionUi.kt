package com.example.quizgame

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class QuestionUi(
    private val rootId: Int,
    private val parent: Matcher<View>,
    private val text: String
) {

    fun checkQuestionState() {
        Espresso.onView(
            Matchers.allOf(
                withId(R.id.questionTextView),
                ViewMatchers.withText(text),
                ViewMatchers.isAssignableFrom(TextView::class.java),
                parent,
                ViewMatchers.withParent(ViewMatchers.withId(rootId)),
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
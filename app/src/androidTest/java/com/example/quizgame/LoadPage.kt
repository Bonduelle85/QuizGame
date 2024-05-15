package com.example.quizgame

import android.widget.LinearLayout
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers

class LoadPage {
    private val rootId: Int = R.id.loadLayout
    private val parent = ViewMatchers.withParent(ViewMatchers.isAssignableFrom(LinearLayout::class.java))

    private val errorUi = ErrorUi(rootId, parent)
    private val progressUi = ProgressUi(rootId, parent)
    private val retryUi = RetryUi(rootId, parent)

    fun checkProgressState() {
        progressUi.checkVisible()
        errorUi.checkNotVisible()
        retryUi.checkNotVisible()
    }

    fun waitUntilError() {
        errorUi.waitUntilVisible()
    }

    fun checkErrorState(message: String) {
        progressUi.checkNotVisible()
        retryUi.checkVisible()
        errorUi.checkVisible(message = message)
    }

    fun clickRetry() {
        retryUi.click()
    }

    fun waitUntilDisappear() {
        Espresso.onView(ViewMatchers.isRoot()).perform(Wait(1100))
    }
}
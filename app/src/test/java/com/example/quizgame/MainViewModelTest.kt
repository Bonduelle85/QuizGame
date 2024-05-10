package com.example.quizgame

import com.example.quizgame.data.MainRepository
import com.example.quizgame.presentation.main.MainViewModel
import com.example.quizgame.presentation.main.Screen
import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest {

    @Test
    fun test() {
        val viewModel = MainViewModel(FakeMainRepository())
        var actual = viewModel.init(true)
        assertEquals(FakeScreen, actual)

        actual = viewModel.init(false)
        assertEquals(Screen.Empty, actual)
    }
}

private class FakeMainRepository : MainRepository {

    override fun lastSavedScreen(): Screen {
        return FakeScreen
    }
}

private object FakeScreen : Screen
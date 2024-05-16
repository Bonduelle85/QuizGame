package com.example.quizgame

import com.example.quizgame.load.data.LoadRepository
import com.example.quizgame.load.data.LoadResult
import com.example.quizgame.load.presentation.LoadUiState
import com.example.quizgame.load.presentation.LoadViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class LoadViewModelTest {

    /**
     * error after progress
     * retry
     * progress and then success
     */
    @Test
    fun test() {
        val repository = FakeLoadRepository()
        val viewModel = LoadViewModel(repository = repository)
        val showUi = FakeShowUi()

        viewModel.init(firstRun = true, showUi = showUi)

        assertEquals(LoadUiState.Progress, showUi.uiStateList[0])
        assertEquals(LoadUiState.Error(message = "failed to fetch data"), showUi.uiStateList[1])

        viewModel.retry(showUi = showUi)

        assertEquals(LoadUiState.Progress, showUi.uiStateList[2])
        assertEquals(LoadUiState.Success, showUi.uiStateList[3])

        //change configuration rotate screen
        assertEquals(4, showUi.uiStateList.size)
        viewModel.init(firstRun = false, showUi = showUi)
        assertEquals(4, showUi.uiStateList.size)
    }
}

class FakeShowUi : (LoadUiState) -> Unit {

    val uiStateList = mutableListOf<LoadUiState>()

    override fun invoke(p1: LoadUiState) {
        uiStateList.add(p1)
    }
}

/*
 * viewModel.init
 * repository.load
 * callback.invoke(LoadResult.Error
 *
 * viewModel.retry
 * repository.load
 * callback.invoke(LoadResult.Success
 */
class FakeLoadRepository : LoadRepository {

    private var returnSuccess = false

    override fun load(callback: (LoadResult) -> Unit) {
        if (returnSuccess) {
            callback.invoke(LoadResult.Success)
        } else {
            callback.invoke(LoadResult.Error(message = "failed to fetch data"))
            returnSuccess = true
        }
    }
}
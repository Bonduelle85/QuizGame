package com.example.quizgame

import com.example.quizgame.load.data.LoadRepository
import com.example.quizgame.load.data.LoadResult
import com.example.quizgame.load.presentation.LoadUiState
import com.example.quizgame.load.presentation.LoadViewModel
import com.example.quizgame.load.presentation.UiObservable
import com.example.quizgame.main.presentation.RunAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
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
        val runAsync = FakeRunAsync()
        val showUi = FakeObservable()
        val viewModel = LoadViewModel(showUi, repository = repository, runAsync = runAsync)

        viewModel.init(firstRun = true)

        assertEquals(true, repository.saveLastScreenIsCalled)

        assertEquals(LoadUiState.Progress, showUi.uiStateList[0])
        assertEquals(LoadUiState.Error(message = "failed to fetch data"), showUi.uiStateList[1])

        viewModel.retry()

        assertEquals(LoadUiState.Progress, showUi.uiStateList[2])
        assertEquals(LoadUiState.Success, showUi.uiStateList[3])

        //change configuration rotate screen
        assertEquals(4, showUi.uiStateList.size)
        viewModel.init(firstRun = false)
        assertEquals(4, showUi.uiStateList.size)
    }
}

class FakeObservable : UiObservable {
    val uiStateList = mutableListOf<LoadUiState>()

    override fun updateObserver(observer: (LoadUiState) -> Unit) {

    }

    override fun clearObserver() {

    }

    override fun updateUiState(uiState: LoadUiState) {
        uiStateList.add(uiState)
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

    override suspend fun load(): LoadResult {
        return if (returnSuccess)
            LoadResult.Success
        else {
            returnSuccess = true
            LoadResult.Error(message = "failed to fetch data")
        }
    }

    var saveLastScreenIsCalled = false

    override fun saveLastScreenIsProgress() {
        saveLastScreenIsCalled = true
    }
}

class FakeRunAsync : RunAsync {

    //    override fun <T : Any> runAsync(background: () -> T, ui: (T) -> Unit) {
    override fun <T : Any> runAsync(coroutineScope: CoroutineScope, background: suspend () -> T, ui: (T) -> Unit) = runBlocking {
        val result = background.invoke()
        ui.invoke(result)
    }

    override fun cancelLastJob() {}
}
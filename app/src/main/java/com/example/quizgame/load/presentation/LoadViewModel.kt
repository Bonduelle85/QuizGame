package com.example.quizgame.load.presentation

import com.example.quizgame.load.data.LoadRepository
import com.example.quizgame.main.presentation.MyViewModel
import com.example.quizgame.main.presentation.RunAsync

class LoadViewModel(
    private val uiObservable: LoadUiObservable,
    private val repository: LoadRepository,
    runAsync: RunAsync,
) : MyViewModel.Abstract(runAsync) {

    fun init(firstRun: Boolean) {
        if (firstRun) {
            repository.saveLastScreenIsProgress()
            uiObservable.updateUiState(LoadUiState.Progress)
            runAsync(repository::load) { loadResult ->
                val uiState = if (loadResult.isSuccessful())
                    LoadUiState.Success
                else
                    LoadUiState.Error(loadResult.message())
                uiObservable.updateUiState(uiState)
            }
        }
    }

    fun retry() = init(true)

    fun startGettingUpdates(showUi: (LoadUiState) -> Unit) {
        uiObservable.updateObserver(showUi)
    }

    fun stopGettingUpdates() {
        uiObservable.clearObserver()
    }
}
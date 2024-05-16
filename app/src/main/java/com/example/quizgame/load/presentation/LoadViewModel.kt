package com.example.quizgame.load.presentation

import com.example.quizgame.load.data.LoadRepository
import com.example.quizgame.main.presentation.MyViewModel
import com.example.quizgame.main.presentation.RunAsync

class LoadViewModel(
    private val repository: LoadRepository,
    runAsync: RunAsync,
) : MyViewModel.Abstract(runAsync) {

    fun init(firstRun: Boolean, showUi: (LoadUiState) -> Unit) {
        if (firstRun) {
            repository.saveLastScreenIsProgress()
            showUi.invoke(LoadUiState.Progress)
            runAsync(repository::load) { loadResult ->
                val uiState = if (loadResult.isSuccessful())
                    LoadUiState.Success
                else
                    LoadUiState.Error(loadResult.message())
                showUi.invoke(uiState)
            }
        }
    }

    fun retry(showUi: (LoadUiState) -> Unit) = init(true, showUi)
}
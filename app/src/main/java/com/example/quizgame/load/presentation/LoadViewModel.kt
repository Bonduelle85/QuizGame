package com.example.quizgame.load.presentation

import com.example.quizgame.load.data.LoadRepository
import com.example.quizgame.main.presentation.MyViewModel

class LoadViewModel(
    private val repository: LoadRepository
) : MyViewModel {

    fun init(firstRun: Boolean, showUi: (LoadUiState) -> Unit) {
        if (firstRun) {
            showUi.invoke(LoadUiState.Progress)
            repository.load { loadResult ->
                if (loadResult.isSuccessful())
                    showUi.invoke(LoadUiState.Success)
                else
                    showUi.invoke(LoadUiState.Error(loadResult.message()))
            }
        }
    }

    fun retry(showUi: (LoadUiState) -> Unit) {
        init(true, showUi)
    }
}
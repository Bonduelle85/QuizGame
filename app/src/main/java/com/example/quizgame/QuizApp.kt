package com.example.quizgame

import android.app.Application
import android.content.Context
import com.example.quizgame.data.MainRepository
import com.example.quizgame.data.Repository
import com.example.quizgame.data.core.IntCache
import com.example.quizgame.data.core.PermanentStorage
import com.example.quizgame.data.core.StringCache
import com.example.quizgame.presentation.game.GameScreen
import com.example.quizgame.presentation.game.GameViewModel
import com.example.quizgame.presentation.main.MainViewModel
import com.example.quizgame.presentation.main.MyViewModel
import com.example.quizgame.presentation.stats.StatsRepository
import com.example.quizgame.presentation.stats.StatsViewModel

class QuizApp : Application(), ManageViewModels {

    private lateinit var factory: ManageViewModels

    override fun onCreate() {
        super.onCreate()
        factory = ProvideViewModel.Factory(ProvideViewModel.Make(Core(this)))
    }

    override fun clear(clazz: Class<out MyViewModel>) {
        factory.clear(clazz)
    }

    override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
        return factory.viewModel(clazz)
    }
}

interface ClearViewModel {

    fun clear(clazz: Class<out MyViewModel>)
}

interface ManageViewModels : ClearViewModel, ProvideViewModel

interface ProvideViewModel {

    fun <T : MyViewModel> viewModel(clazz: Class<T>): T

    class Factory(
        private val make: ProvideViewModel
    ) : ManageViewModels {

        private val mutableMap = mutableMapOf<Class<out MyViewModel>, MyViewModel?>()

        override fun clear(clazz: Class<out MyViewModel>) {
            mutableMap[clazz] = null
        }

        override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
            return if (mutableMap[clazz] == null) {
                val viewModel = make.viewModel(clazz)
                mutableMap[clazz] = viewModel
                viewModel
            } else
                mutableMap[clazz] as T
        }
    }

    class Make(private val core: Core) : ProvideViewModel {//todo use chain of responsibility to refactor

        override fun <T : MyViewModel> viewModel(clazz: Class<T>): T = with(core) {
            return when (clazz) {
                MainViewModel::class.java -> MainViewModel(MainRepository.Base(lastScreen))

                StatsViewModel::class.java -> StatsViewModel(
                    StatsRepository.Base(
                        lastScreen,
                        corrects,
                        incorrects
                    )
                )

                GameViewModel::class.java -> GameViewModel(
                    Repository.Base(
                        lastScreen,
                        corrects,
                        incorrects,
                        IntCache.Base("currentIndex", permanentStorage, 0),
                        IntCache.Base("userChoiceIndex", permanentStorage, -1)
                    )
                )

                else -> throw IllegalStateException("unknown viewModel $clazz go and add it to ProvideViewModel.Make")
            } as T
        }
    }
}

class Core(context: Context) {
    val lastScreen: StringCache
    val corrects: IntCache
    val incorrects: IntCache
    val permanentStorage: PermanentStorage

    init {
        val name = context.getString(R.string.app_name)
        permanentStorage = PermanentStorage.Base(
            context.getSharedPreferences(
                name, Context.MODE_PRIVATE
            )
        )
        corrects = IntCache.Base("corrects", permanentStorage, 0)
        incorrects = IntCache.Base("incorrects", permanentStorage, 0)

        lastScreen =
            StringCache.Base("lastScreen", permanentStorage, GameScreen::class.java.canonicalName)
    }
}
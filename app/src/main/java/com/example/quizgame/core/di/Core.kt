package com.example.quizgame.core.di

import android.content.Context
import com.example.quizgame.R
import com.example.quizgame.core.data.IntCache
import com.example.quizgame.core.data.PermanentStorage
import com.example.quizgame.core.data.StringCache
import com.example.quizgame.load.data.cache.CacheModule
import com.example.quizgame.load.presentation.LoadScreen
import com.example.quizgame.main.presentation.RunAsync

class Core(context: Context) {
    val cacheModule: CacheModule = CacheModule.Base(context)
    val runAsync: RunAsync = RunAsync.Base()
    val lastScreen: StringCache
    val corrects: IntCache
    val incorrects: IntCache
    val permanentStorage: PermanentStorage
    val max: Int

    val runUiTest: Boolean = false

    init {
        val name = if (runUiTest) "uitestname" else context.getString(R.string.app_name)
        max = if (runUiTest) 3 else 10
        permanentStorage = PermanentStorage.Base(
            context.getSharedPreferences(
                name, Context.MODE_PRIVATE
            )
        )

        corrects = IntCache.Base("corrects", permanentStorage, 0)
        incorrects = IntCache.Base("incorrects", permanentStorage, 0)

        lastScreen =
            StringCache.Base("lastScreen", permanentStorage, LoadScreen::class.java.canonicalName!!)
    }
}
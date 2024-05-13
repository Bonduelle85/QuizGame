package com.example.quizgame.core.di

import android.content.Context
import com.example.quizgame.R
import com.example.quizgame.core.data.IntCache
import com.example.quizgame.core.data.PermanentStorage
import com.example.quizgame.core.data.StringCache
import com.example.quizgame.game.presentation.GameScreen

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
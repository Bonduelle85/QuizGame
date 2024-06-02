package com.example.quizgame.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.quizgame.load.data.cache.QuestionAndChoicesDao
import com.example.quizgame.load.data.cache.QuestionCache
import com.example.quizgame.load.data.cache.QuizDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomTest {

    private lateinit var dao: QuestionAndChoicesDao
    private lateinit var db: QuizDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, QuizDatabase::class.java
        ).build()
        dao = db.dao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    /**
     * Save 3
     * check all them
     * save 2
     * check new 2 and third is old one
     */
    @Test
    fun test() = runBlocking {
        dao.save(
            listOf(
                QuestionCache(1, "1", "c1", "i1", "i2", "i3"),
                QuestionCache(2, "2", "c2", "i12", "i22", "i32"),
                QuestionCache(3, "3", "c3", "i13", "i23", "i33"),
            )
        )

        assertEquals(
            QuestionCache(1, "1", "c1", "i1", "i2", "i3"),
            dao.questionAndChoices(1)
        )
        assertEquals(
            QuestionCache(2, "2", "c2", "i12", "i22", "i32"),
            dao.questionAndChoices(2)
        )
        assertEquals(
            QuestionCache(3, "3", "c3", "i13", "i23", "i33"),
            dao.questionAndChoices(3)
        )

        dao.save(
            listOf(
                QuestionCache(1, "new1", "newc1", "newi1", "newi2", "newi3"),
                QuestionCache(2, "new2", "newc2", "newi12", "newi22", "newi32"),
            )
        )

        assertEquals(
            QuestionCache(1, "new1", "newc1", "newi1", "newi2", "newi3"),
            dao.questionAndChoices(1)
        )
        assertEquals(
            QuestionCache(2, "new2", "newc2", "newi12", "newi22", "newi32"),
            dao.questionAndChoices(2)
        )
        assertEquals(
            QuestionCache(3, "3", "c3", "i13", "i23", "i33"),
            dao.questionAndChoices(3)
        )
    }
}
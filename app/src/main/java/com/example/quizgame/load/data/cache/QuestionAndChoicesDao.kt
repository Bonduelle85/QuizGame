package com.example.quizgame.load.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionAndChoicesDao {

    @Query("select * from question_table where id= :currentIndex")
    suspend fun questionAndChoices(currentIndex: Int): QuestionCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(list: List<QuestionCache>)
}
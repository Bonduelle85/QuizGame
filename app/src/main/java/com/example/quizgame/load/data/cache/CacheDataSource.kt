package com.example.quizgame.load.data.cache

import com.example.quizgame.load.data.cloud.QuestionAndChoicesCloud
import com.example.quizgame.load.data.cloud.ResponseCloud

interface CacheDataSource {

    suspend fun save(data: ResponseCloud)

    suspend fun read(index: Int): QuestionAndChoicesCloud

    class Base(
        private val dao: QuestionAndChoicesDao,
    ) : CacheDataSource {
        override suspend fun save(data: ResponseCloud) {
            dao.save(data.items.mapIndexed { index, questionAndChoicesCloud ->
                QuestionCache(
                    id = index,
                    question = questionAndChoicesCloud.question,
                    correct = questionAndChoicesCloud.correct,
                    incorrectOne = questionAndChoicesCloud.incorrects[0],
                    incorrectTwo = questionAndChoicesCloud.incorrects[1],
                    incorrectThree = questionAndChoicesCloud.incorrects[2]
                )
            })
        }

        override suspend fun read(index: Int): QuestionAndChoicesCloud {
            val cache = dao.questionAndChoices(index)
            return QuestionAndChoicesCloud(
                question = cache.question,
                correct = cache.correct,
                incorrects = listOf(cache.incorrectOne, cache.incorrectTwo, cache.incorrectThree)
            )
        }

    }
}
package com.example.quizgame.data

interface CheckResult {
    class Correct(val correctIndex: Int) : CheckResult

    class Incorrect(
        val correctIndex: Int,
        val incorrectIndex: Int
    ) : CheckResult
}
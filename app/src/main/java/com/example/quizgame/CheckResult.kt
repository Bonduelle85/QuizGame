package com.example.quizgame

interface CheckResult {
    class Correct(val correctIndex: Int) : CheckResult

    class Incorrect(
        val correctIndex: Int,
        val incorrectIndex: Int
    ) : CheckResult
}
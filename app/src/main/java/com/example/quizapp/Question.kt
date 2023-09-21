package com.example.quizapp

data class Question(
    val question: String,
    val choices: List<String>,
    val answer: String
)

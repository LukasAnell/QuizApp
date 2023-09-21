package com.example.quizapp

data class Quiz(
    val questions: List<Question>
) {
    fun checkAnswer(questionNumber: Int, choice: Int): Int {
        val question = questions[questionNumber]
        val answer = question.answer
        return if(question.choices[choice] == answer) {
            1
        } else {
            0
        }
    }
}

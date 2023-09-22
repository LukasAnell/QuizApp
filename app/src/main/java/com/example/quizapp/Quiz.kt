package com.example.quizapp

data class Quiz(
    val questions: List<Question>
) {
    fun getQuestion(questionNumber: Int): String {
        val question = questions[questionNumber].question
        return question
    }

    fun getChoices(questionNumber: Int): List<String> {
        val choices = questions[questionNumber].choices
        return choices
    }

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

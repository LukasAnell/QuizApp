package com.example.quizapp

data class Quiz(
    val questions: List<Question>
) {
    var score = 0
    private var questionNumber = 0
    fun getQuestion(questionNumber: Int): String {
        return questions[questionNumber].question
    }

    fun getChoices(questionNumber: Int): List<String> {
        return questions[questionNumber].choices
    }

    private fun checkAnswer(questionNumber: Int, choice: Int): Int {
        val question = questions[questionNumber]
        val answer = question.answer
        return if(question.choices[choice] == answer) {
            1
        } else {
            0
        }
    }

    fun getQuestionNumber(): Int {
        return questionNumber
    }

    private fun buttonClick(choice: Int): Boolean {
        return if(questionNumber < questions.size - 1) {
            score += checkAnswer(questionNumber, choice)
            questionNumber++
            true
        } else {
            if(questionNumber == questions.size - 1) {
                score += checkAnswer(questionNumber, choice)
                questionNumber++
            }
            false
        }
    }

    fun buttonOne(): Boolean {
        return buttonClick(0)
    }

    fun buttonTwo(): Boolean {
        return buttonClick(1)
    }

    fun buttonThree(): Boolean {
        return buttonClick(2)
    }

    fun buttonFour(): Boolean {
        return buttonClick(3)
    }
}

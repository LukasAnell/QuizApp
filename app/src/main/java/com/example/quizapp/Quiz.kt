package com.example.quizapp

data class Quiz(
    val questions: List<Question>
) {
    var score = 0
    private var questionNumber = 0
    fun getQuestion(questionNumber: Int): String {
        val question = questions[questionNumber].question
        return question
    }

    fun getChoices(questionNumber: Int): List<String> {
        val choices = questions[questionNumber].choices
        return choices
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

    fun buttonOne(): Boolean {
        return if(questionNumber < questions.size - 1) {
            score += checkAnswer(questionNumber, 0)
            questionNumber++
            true
        } else {
            if(questionNumber == questions.size - 1) {
                score += checkAnswer(questionNumber, 0)
                questionNumber++
            }
            false
        }
    }

    fun buttonTwo(): Boolean {
        return if(questionNumber < questions.size - 1) {
            score+= checkAnswer(questionNumber, 1)
            questionNumber++
            true
        } else {
            if(questionNumber == questions.size - 1) {
                score += checkAnswer(questionNumber, 1)
                questionNumber++
            }
            false
        }
    }

    fun buttonThree(): Boolean {
        return if(questionNumber < questions.size - 1) {
            score += checkAnswer(questionNumber, 2)
            questionNumber++
            true
        } else {
            if(questionNumber == questions.size - 1) {
                score += checkAnswer(questionNumber, 2)
                questionNumber++
            }
            false
        }
    }

    fun buttonFour(): Boolean {
        return if(questionNumber < questions.size - 1) {
            score += checkAnswer(questionNumber, 3)
            questionNumber++
            true
        } else {
            if(questionNumber == questions.size - 1) {
                score += checkAnswer(questionNumber, 3)
                questionNumber++
            }
            false
        }
    }
}

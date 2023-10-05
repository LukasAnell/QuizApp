package com.example.quizapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private lateinit var layoutMain: ConstraintLayout
    private lateinit var textViewQuestion: TextView
    private lateinit var buttonChoiceOne: Button
    private lateinit var buttonChoiceTwo: Button
    private lateinit var buttonChoiceThree: Button
    private lateinit var buttonChoiceFour: Button
    private lateinit var textViewScore: TextView

    companion object {
        const val TAG = "MainActivity"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidgets()
        buttonChoiceOne.textSize = 30.0F
        buttonChoiceTwo.textSize = 30.0F
        buttonChoiceThree.textSize = 30.0F
        buttonChoiceFour.textSize = 30.0F
        textViewQuestion.textSize = 15.0F
        textViewScore.textSize = 15.0F

        // load questions from JSON (we will learn how to do this next class)
        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        Log.d(TAG, "onCreate: jsonString $jsonString")

        val gson = Gson()
        val sType = object: TypeToken<List<Question>>() { }.type
        val otherList = gson.fromJson<List<Question>>(jsonString, sType)

        // create a Quiz object and pass in that list of questions
        // as a parameter
        val newQuiz = Quiz(otherList)
        textViewScore.text = "${resources.getString(R.string.score)}: ${newQuiz.score}"

        Log.d(TAG, "Q: ${newQuiz.questions[0].question}\nC:${newQuiz.questions[0].choices}\nA:${newQuiz.questions[0].answer}")

        // do the initial question & answer choices setup


        textViewQuestion.text = newQuiz.getQuestion(newQuiz.getQuestionNumber())
        var choices = newQuiz.getChoices(newQuiz.getQuestionNumber())
        buttonChoiceOne.text = choices[0]
        buttonChoiceTwo.text = choices[1]
        buttonChoiceThree.text = choices[2]
        buttonChoiceFour.text = choices[3]

        buttonChoiceOne.setOnClickListener {
            if(newQuiz.buttonOne()) {
                textViewQuestion.text = newQuiz.getQuestion(newQuiz.getQuestionNumber())
                choices = newQuiz.getChoices(newQuiz.getQuestionNumber())
                buttonChoiceOne.text = choices[0]
                buttonChoiceTwo.text = choices[1]
                buttonChoiceThree.text = choices[2]
                buttonChoiceFour.text = choices[3]
                textViewScore.text = "${resources.getString(R.string.score)}: ${newQuiz.score}"
                Log.d(TAG, "${resources.getString(R.string.score)}: ${newQuiz.score}")
            } else {
                finished()
                textViewScore.text = "${resources.getString(R.string.score)}: ${newQuiz.score}"
            }
        }
        buttonChoiceTwo.setOnClickListener {
            if(newQuiz.buttonTwo()) {
                textViewQuestion.text = newQuiz.getQuestion(newQuiz.getQuestionNumber())
                choices = newQuiz.getChoices(newQuiz.getQuestionNumber())
                buttonChoiceOne.text = choices[0]
                buttonChoiceTwo.text = choices[1]
                buttonChoiceThree.text = choices[2]
                buttonChoiceFour.text = choices[3]
                textViewScore.text = "${resources.getString(R.string.score)}: ${newQuiz.score}"
                Log.d(TAG, "${resources.getString(R.string.score)}: ${newQuiz.score}")
            } else {
                finished()
                textViewScore.text = "${resources.getString(R.string.score)}: ${newQuiz.score}"
            }
        }
        buttonChoiceThree.setOnClickListener {
            if(newQuiz.buttonThree()) {
                textViewQuestion.text = newQuiz.getQuestion(newQuiz.getQuestionNumber())
                choices = newQuiz.getChoices(newQuiz.getQuestionNumber())
                buttonChoiceOne.text = choices[0]
                buttonChoiceTwo.text = choices[1]
                buttonChoiceThree.text = choices[2]
                buttonChoiceFour.text = choices[3]
                textViewScore.text = "${resources.getString(R.string.score)}: ${newQuiz.score}"
                Log.d(TAG, "${resources.getString(R.string.score)}: ${newQuiz.score}")
            } else {
                finished()
                textViewScore.text = "${resources.getString(R.string.score)}: ${newQuiz.score}"
            }
        }
        buttonChoiceFour.setOnClickListener {
            if(newQuiz.buttonFour()) {
                textViewQuestion.text = newQuiz.getQuestion(newQuiz.getQuestionNumber())
                choices = newQuiz.getChoices(newQuiz.getQuestionNumber())
                buttonChoiceOne.text = choices[0]
                buttonChoiceTwo.text = choices[1]
                buttonChoiceThree.text = choices[2]
                buttonChoiceFour.text = choices[3]
                textViewScore.text = "${resources.getString(R.string.score)}: ${newQuiz.score}"
                Log.d(TAG, "${resources.getString(R.string.score)}: ${newQuiz.score}")
            } else {
                finished()
                textViewScore.text = "${resources.getString(R.string.score)}: ${newQuiz.score}"
            }
        }
        // set listeners to react to user input
            // passing info to and from the Quiz object
    }

    private fun finished() {
        buttonChoiceOne.text = resources.getString(R.string.done)
        buttonChoiceTwo.text = resources.getString(R.string.done)
        buttonChoiceThree.text = resources.getString(R.string.done)
        buttonChoiceFour.text = resources.getString(R.string.done)
        textViewQuestion.text = ""
        textViewScore.textSize = 30.0F
    }

    private fun wireWidgets() {
        layoutMain = findViewById(R.id.layout_main)
        textViewQuestion = findViewById(R.id.textView_main_question)
        buttonChoiceOne = findViewById(R.id.button_main_c1)
        buttonChoiceTwo = findViewById(R.id.button_main_c2)
        buttonChoiceThree = findViewById(R.id.button_main_c3)
        buttonChoiceFour = findViewById(R.id.button_main_c4)
        textViewScore = findViewById(R.id.textView_main_score)
    }
}
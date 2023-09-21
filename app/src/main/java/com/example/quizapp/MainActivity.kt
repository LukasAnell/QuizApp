package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var layoutMain: ConstraintLayout
    lateinit var textViewQuestion: TextView
    lateinit var buttonChoiceOne: Button
    lateinit var buttonChoiceTwo: Button
    lateinit var buttonChoiceThree: Button
    lateinit var buttonChoiceFour: Button
    lateinit var textViewScore: TextView
    var score = 0

    companion object {
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidgets()
        resizeButtonText(30.0F)
        textViewQuestion.text = "Score: $score"
        textViewQuestion.textSize = 10.0F

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
        Log.d(TAG, "Q: ${newQuiz.questions[0].question}\nC:${newQuiz.questions[0].choices}\nA:${newQuiz.questions[0].answer}")

        // do the initial question & answer choices setup
        for(i in 0..<newQuiz.questions.size) {
            textViewQuestion.text = newQuiz.questions[i].question
            buttonChoiceOne.text = newQuiz.questions[i].choices[0]
            buttonChoiceTwo.text = newQuiz.questions[i].choices[1]
            buttonChoiceThree.text = newQuiz.questions[i].choices[2]
            buttonChoiceFour.text = newQuiz.questions[i].choices[3]
            buttonChoiceOne.setOnClickListener {
                score += newQuiz.checkAnswer(i, 0)
            }
            buttonChoiceTwo.setOnClickListener {
                score += newQuiz.checkAnswer(i, 1)
            }
            buttonChoiceThree.setOnClickListener {
                score += newQuiz.checkAnswer(i, 2)
            }
            buttonChoiceFour.setOnClickListener {
                score += newQuiz.checkAnswer(i, 3)
            }
            textViewScore.text = "Score: $score"
        }
        Log.d(TAG, "Score: $score")
        // set listeners to react to user input
            // passing info to and from the Quiz object
    }

    private fun resizeButtonText(size: Float) {
        buttonChoiceOne.textSize = size
        buttonChoiceTwo.textSize = size
        buttonChoiceThree.textSize = size
        buttonChoiceFour.textSize = size
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
package com.example.quizgame

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Level2CapitalActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var option1: Button
    private lateinit var option2: Button
    private lateinit var option3: Button
    private lateinit var option4: Button
    private lateinit var scoreTextView: TextView
    private var currentQuestionIndex = 0
    private var score = 0

    private val questions = listOf(
        Question("Аргентина", "Буэнос-Айрес", "Канберра", "Нью-Дели", "Амстердам", 1),
        Question("Австралия", "Бразилиа", "Канберра", "Сеул", "Стокгольм", 2),
        Question("Индия", "Токио", "Париж", "Нью-Дели", "Берн", 3),
        Question("Нидерланды", "Оттава", "Амстердам", "Тегеран", "Лиссабон", 2),
        Question("Бразилия", "Берлин", "Сеул", "Бразилиа", "Москва", 3),
        Question("Южная Корея", "Стокгольм", "Берн", "Нью-Дели", "Сеул", 4),
        Question("Швеция", "Стокгольм", "Хельсинки", "Берлин", "Мадрид", 1),
        Question("Швейцария", "Берн", "Лиссабон", "Варшава", "Дублин", 1),
        Question("Иран", "Тегеран", "Афины", "Рим", "Вена", 1),
        Question("Португалия", "Мадрид", "Лиссабон", "Брюссель", "Прага", 2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level1_capital)

        questionTextView = findViewById(R.id.questionTextView)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)
        scoreTextView = findViewById(R.id.scoreTextView)

        loadQuestion()

        val optionClickListener = { button: Button ->
            checkAnswer(button.text.toString())
        }

        option1.setOnClickListener { optionClickListener(option1) }
        option2.setOnClickListener { optionClickListener(option2) }
        option3.setOnClickListener { optionClickListener(option3) }
        option4.setOnClickListener { optionClickListener(option4) }
    }

    private fun loadQuestion() {
        if (currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]
            questionTextView.text = currentQuestion.question
            option1.text = currentQuestion.option1
            option2.text = currentQuestion.option2
            option3.text = currentQuestion.option3
            option4.text = currentQuestion.option4
        } else {
            finishLevel()
        }
    }

    private fun checkAnswer(selectedAnswer: String) {
        val currentQuestion = questions[currentQuestionIndex]
        if (selectedAnswer == currentQuestion.correctAnswer) {
            score++
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
        }
        currentQuestionIndex++
        loadQuestion()
        scoreTextView.text = "Score: $score"
    }

    private fun finishLevel() {
        Toast.makeText(this, "Level Completed!", Toast.LENGTH_LONG).show()

        // Обновление очков пользователя
        val sharedPrefs = getSharedPreferences("GamePrefs", MODE_PRIVATE)
        val username = sharedPrefs.getString("loggedInUser", "Guest") ?: "Guest"
        val dbHelper = DatabaseHelper(this)
        val currentScore = dbHelper.getUserScore(username)
        dbHelper.updateUserScore(username, currentScore + score)

        finish()
    }

    data class Question(
        val question: String,
        val option1: String,
        val option2: String,
        val option3: String,
        val option4: String,
        val correctOption: Int
    ) {
        val correctAnswer: String
            get() = when (correctOption) {
                1 -> option1
                2 -> option2
                3 -> option3
                4 -> option4
                else -> ""
            }
    }
}

package com.example.quizgame

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Level1FlagActivity : AppCompatActivity() {

    private lateinit var flagImageView: ImageView
    private lateinit var option1: Button
    private lateinit var option2: Button
    private lateinit var option3: Button
    private lateinit var option4: Button
    private lateinit var scoreTextView: TextView
    private var currentQuestionIndex = 0
    private var score = 0

    private val questions = listOf(
        Question(R.drawable.ca, "Канада", "Мексика", "Норвегия", "Индия", 1),
        Question(R.drawable.ru, "Финляндия", "Россия", "Казахстан", "Монголия", 2),
        Question(R.drawable.us, "США", "Куба", "Австралия", "Бразилия", 1),
        Question(R.drawable.cn, "Китай", "Япония", "Корея", "Таиланд", 1),
        Question(R.drawable.fr, "Франция", "Испания", "Греция", "Швейцария", 1),
        Question(R.drawable.it, "Португалия", "Италия", "Хорватия", "Албания", 2),
        Question(R.drawable.tr, "Египет", "Турция", "Ирак", "Иран", 2),
        Question(R.drawable.jp, "Корея", "Китай", "Тайвань", "Япония", 4),
        Question(R.drawable.de, "Германия", "Нидерланды", "Польша", "Чехия", 1),
        Question(R.drawable.gb, "Ирландия", "Великобритания", "Канада", "Австралия", 2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level1)

        flagImageView = findViewById(R.id.flagImageView)
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
            flagImageView.setImageResource(currentQuestion.flagResource)
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
        val flagResource: Int,
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
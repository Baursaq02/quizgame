package com.example.quizgame

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity() {

    private lateinit var resultsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        resultsTextView = findViewById(R.id.resultsTextView)

        val dbHelper = DatabaseHelper(this)
        val allResults = dbHelper.getAllUsersWithScores()

        resultsTextView.text = allResults.joinToString("\n") { "Ваш Ник: ${it.first}, Очки: ${it.second}" }
    }
}
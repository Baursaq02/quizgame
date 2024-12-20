package com.example.quizgame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var guessByFlagButton: Button
    private lateinit var guessByCapitalButton: Button
    private lateinit var viewResultsButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guessByFlagButton = findViewById(R.id.guessByFlagButton)
        guessByCapitalButton = findViewById(R.id.guessByCapitalButton)
        viewResultsButton = findViewById(R.id.viewResultsButton)

        guessByFlagButton.setOnClickListener {
            val intent = Intent(this, LevelSelectionActivity::class.java)
            intent.putExtra("gameMode", "flag")
            startActivity(intent)
        }

        guessByCapitalButton.setOnClickListener {
            val intent = Intent(this, LevelSelectionActivity::class.java)
            intent.putExtra("gameMode", "capital")
            startActivity(intent)
        }

        // Обработчик кнопки для перехода к результатам
        viewResultsButton.setOnClickListener {
            val intent = Intent(this, ResultsActivity::class.java)
            startActivity(intent)
        }
    }
}

package com.example.quizgame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LevelSelectionActivity : AppCompatActivity() {

    private lateinit var level1Button: Button
    private lateinit var level2Button: Button
    private lateinit var level3Button: Button
    private lateinit var level4Button: Button
    private lateinit var level5Button: Button
    private lateinit var level6Button: Button

    private var gameMode: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_selection)

        gameMode = intent.getStringExtra("gameMode")

        level1Button = findViewById(R.id.level1Button)
        level2Button = findViewById(R.id.level2Button)
        level3Button = findViewById(R.id.level3Button)
        level4Button = findViewById(R.id.level4Button)
        level5Button = findViewById(R.id.level5Button)
        level6Button = findViewById(R.id.level6Button)

        level1Button.setOnClickListener { startLevel(1) }
        level2Button.setOnClickListener { startLevel(2) }
        level3Button.setOnClickListener { startLevel(3) }
        level4Button.setOnClickListener { startLevel(4) }
        level5Button.setOnClickListener { startLevel(5) }
        level6Button.setOnClickListener { startLevel(6) }
    }

    private fun startLevel(level: Int) {
        val intent = when {
            gameMode == "flag" && level == 1 -> Intent(this, Level1FlagActivity::class.java)
            gameMode == "flag" && level == 2 -> Intent(this, Level2FlagActivity::class.java)
            gameMode == "flag" && level == 3 -> Intent(this, Level3FlagActivity::class.java)
            gameMode == "flag" && level == 4 -> Intent(this, Level4FlagActivity::class.java)
            gameMode == "flag" && level == 5 -> Intent(this, Level5FlagActivity::class.java)
            gameMode == "flag" && level == 6 -> Intent(this, Level6FlagActivity::class.java)

            gameMode == "capital" && level == 1 -> Intent(this, Level1CapitalActivity::class.java)
            gameMode == "capital" && level == 2 -> Intent(this, Level2CapitalActivity::class.java)
            gameMode == "capital" && level == 3 -> Intent(this, Level3CapitalActivity::class.java)
            gameMode == "capital" && level == 4 -> Intent(this, Level4CapitalActivity::class.java)
            gameMode == "capital" && level == 5 -> Intent(this, Level5CapitalActivity::class.java)
            gameMode == "capital" && level == 6 -> Intent(this, Level6CapitalActivity::class.java)

            else -> return
        }
        startActivity(intent)
    }
}
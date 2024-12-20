package com.example.quizgame

import android.content.Context
import java.io.File

class DatabaseHelper(context: Context) {

    private val file = File(context.filesDir, "users.txt")

    init {
        if (!file.exists()) {
            file.createNewFile()
        }
    }

    fun registerUser(username: String, password: String) {
        file.appendText("$username,$password,0\n")
    }

    fun userExists(username: String): Boolean {
        return file.readLines().any { it.split(",")[0] == username }
    }

    fun validateUser(username: String, password: String): Boolean {
        return file.readLines().any {
            val parts = it.split(",")
            parts[0] == username && parts[1] == password
        }
    }

    fun getUserScore(username: String): Int {
        return file.readLines().find { it.split(",")[0] == username }?.split(",")?.get(2)?.toInt() ?: 0
    }

    fun updateUserScore(username: String, score: Int) {
        val lines = file.readLines()
        val updatedLines = lines.map {
            val parts = it.split(",")
            if (parts[0] == username) {
                "${parts[0]},${parts[1]},$score"
            } else {
                it
            }
        }
        file.writeText(updatedLines.joinToString("\n"))
    }

    fun getAllUsersWithScores(): List<Pair<String, Int>> {
        return file.readLines().mapNotNull {
            val parts = it.split(",")
            if (parts.size == 3) {
                parts[0] to parts[2].toInt()
            } else null
        }
    }
}
package com.defendend.tetris.storage

import android.content.Context
import android.content.SharedPreferences

private const val KEY = "HIGH_SCORE"
private const val DEFAULT_VALUE: Int = 0

class AppPreferences(ctx: Context) {
    private val sharedPrefs: SharedPreferences =
        ctx.getSharedPreferences("APP_PREFERENCES", Context.MODE_PRIVATE)

    fun saveHighScore(highScore: Int) {
        sharedPrefs.edit().putInt(KEY, highScore).apply()
    }

    fun getHighScore(): Int {
        return sharedPrefs.getInt(KEY, DEFAULT_VALUE)
    }

    fun clearHighScore() {
        sharedPrefs.edit().putInt(KEY, DEFAULT_VALUE).apply()
    }

}
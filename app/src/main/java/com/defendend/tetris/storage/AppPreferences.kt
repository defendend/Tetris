package com.defendend.tetris.storage

import android.content.Context
import android.content.SharedPreferences

private const val KEY = "HIGH_SCORE"
private const val DEFAULT_VALUE : Int = 0

class AppPreferences(ctx: Context) {
    var data: SharedPreferences = ctx.getSharedPreferences("APP_PREFERENCES", Context.MODE_PRIVATE)

    fun saveHighScore(highScore: Int){
        data.edit().putInt(KEY,highScore).apply()
    }

    fun getHighScore() : Int {
        return data.getInt(KEY, DEFAULT_VALUE)
    }

    fun clearHighScore() {
        data.edit().putInt(KEY, DEFAULT_VALUE)
    }

}
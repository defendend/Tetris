package com.defendend.tetris

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.defendend.tetris.databinding.ActivityMainBinding
import com.defendend.tetris.storage.AppPreferences
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var preferences: AppPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {
            btnNewGame.setOnClickListener(::onBtnNewGameClick)
            btnResetScore.setOnClickListener(::onBtnResetScoreClick)
            btnExit.setOnClickListener(::onBtnExitClick)
        }
        preferences = AppPreferences(this)
        binding?.tvHighScore?.text = getString(R.string.text_score, preferences?.getHighScore())

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun onBtnNewGameClick(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    private fun onBtnResetScoreClick(view: View) {
        preferences?.clearHighScore()
        Snackbar.make(view, R.string.successful_reset, Snackbar.LENGTH_SHORT).show()
        binding?.tvHighScore?.text = getString(R.string.text_score, preferences?.getHighScore())
    }

    private fun onBtnExitClick(view: View) {
        exitProcess(0)
    }
}
package com.defendend.tetris

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.defendend.tetris.databinding.ActivityGameBinding
import com.defendend.tetris.models.AppModel
import com.defendend.tetris.storage.AppPreferences

class GameActivity : AppCompatActivity() {

    var appPreferences: AppPreferences? = null

    var binding: ActivityGameBinding? = null
    private val appModel: AppModel = AppModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        appPreferences = AppPreferences(this)
        appModel.setPreferences(appPreferences)

        binding?.apply {
            viewTetris.setActivity(this@GameActivity)
            viewTetris.setModel(appModel)
            viewTetris.setOnTouchListener(::onTetrisViewTouch)
            btnRestart.setOnClickListener(::btnRestartClick)
        }

        updateHighScore()
        updateCurrentScore()
    }

    private fun btnRestartClick(view: View) {
        appModel.restartGame()
    }

    private fun onTetrisViewTouch(view: View, event: MotionEvent): Boolean {
        if (appModel.isGameOver() || appModel.isGameAwaitingStart()) {
            appModel.startGame()
            binding?.viewTetris?.setGameCommandWithDelay(AppModel.Motions.DOWN)
        } else if (appModel.isGameActive()) {
            when (resolveTouchDirection(view, event)) {
                0 -> moveTetromino(AppModel.Motions.LEFT)
                1 -> moveTetromino(AppModel.Motions.ROTATE)
                2 -> moveTetromino(AppModel.Motions.DOWN)
                3 -> moveTetromino(AppModel.Motions.RIGHT)
            }
        }
        return true
    }

    private fun resolveTouchDirection(view: View, event: MotionEvent): Int {
        val x = event.x / view.width
        val y = event.y / view.height
        return if (y > x) {
            if (x > 1 - y) 2 else 0
        } else {
            if (x > 1 - y) 3 else 1
        }
    }

    private fun moveTetromino(motion: AppModel.Motions) {
        if (appModel.isGameActive()) {
            binding?.viewTetris?.setGameCommand(motion)
        }
    }

    private fun updateHighScore() {
        binding?.tvHighScore?.text = "${appPreferences?.getHighScore()}"
    }

    private fun updateCurrentScore() {
       binding?.tvCurrentScore?.text = "0"
    }

}
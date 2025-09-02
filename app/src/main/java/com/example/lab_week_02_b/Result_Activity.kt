package com.example.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColorInt

class ResultActivity : AppCompatActivity() {

    companion object {
        const val COLOR_KEY = "COLOR_KEY"
        const val ERROR_KEY = "ERROR_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
        val resultMessage = findViewById<TextView>(R.id.color_code_result_message)

        val colorCode = intent.getStringExtra(COLOR_KEY)

        if (colorCode.isNullOrBlank()) {
            setResult(RESULT_OK, Intent().putExtra(ERROR_KEY, true)) // no redundant qualifier
            finish()
            return
        }

        try {
            backgroundScreen.setBackgroundColor("#$colorCode".toColorInt())
            resultMessage.text = getString(
                R.string.color_code_result_message,
                colorCode.uppercase()
            )
        } catch (_: IllegalArgumentException) {
            setResult(RESULT_OK, Intent().putExtra(ERROR_KEY, true)) // no redundant qualifier
            finish()
        }
    }
}

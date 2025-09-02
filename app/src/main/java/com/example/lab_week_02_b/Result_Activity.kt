package com.example.lab_week_02_b

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColorInt

class ResultActivity : AppCompatActivity() {

    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
        val resultMessage = findViewById<TextView>(R.id.color_code_result_message)

        val colorCode = intent.getStringExtra(COLOR_KEY)

        colorCode?.let {
            try {
                backgroundScreen.setBackgroundColor(("#$it").toColorInt())
                resultMessage.text = getString(
                    R.string.color_code_result_message,
                    it.uppercase()
                )
            } catch (_: IllegalArgumentException) {
                // fallback if somehow invalid code passed
                backgroundScreen.setBackgroundColor(android.graphics.Color.WHITE)
                resultMessage.text = getString(R.string.color_code_input_invalid)
            }
        } ?: run {
            // if null, show fallback
            backgroundScreen.setBackgroundColor(android.graphics.Color.WHITE)
            resultMessage.text = getString(R.string.color_code_input_empty)
        }
    }
}

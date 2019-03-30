package com.example.smartgallerywizard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.double_column_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        setContentView(R.layout.double_column_layout)

        createListenerOnClickButton()
    }

    private fun createListenerOnClickButton() {
        val clickSecondButton = findViewById<Button>(R.id.second_click_button)
        clickSecondButton.setOnClickListener {
            clickSecondButton.setOnClickListener {
                this@SecondActivity.startActivity(Intent(this@SecondActivity,
                        MainActivity::class.java))
            }
        }
    }
}
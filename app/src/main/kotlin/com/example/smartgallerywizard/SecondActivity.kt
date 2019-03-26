package com.example.smartgallerywizard

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_NEUTRAL
import android.content.Intent


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.double_column_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.double_column_layout)

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
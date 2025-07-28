package com.example.android65_project

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerButton: Button = findViewById(R.id.button_register)

        registerButton.setOnClickListener {
            Toast.makeText(this, "Registration logic goes here.", Toast.LENGTH_SHORT).show()

            finish()
        }
    }
}
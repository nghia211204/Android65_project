package com.example.android65_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sessionManager = SessionManager(this)

        val loginButton: Button = findViewById(R.id.button_login)
        val goToRegisterText: TextView = findViewById(R.id.text_view_go_to_register)

        loginButton.setOnClickListener {
            sessionManager.setLogin(true)
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

            finish()
        }

        goToRegisterText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
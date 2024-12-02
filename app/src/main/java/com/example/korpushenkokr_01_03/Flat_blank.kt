package com.example.korpushenkokr_01_03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Flat_blank : AppCompatActivity() {
    private lateinit var log: EditText
    private lateinit var pass: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flat_blank)
        log = findViewById<EditText>(R.id.usernameEditText)
        pass = findViewById(R.id.userpasswordEditText)
        loginButton = findViewById<Button>(R.id.loginButton)
        getUserInfo()
        loginButton.setOnClickListener(View.OnClickListener {
            if (log.text.toString() != "" && pass.text.toString() != "") {
                val username = log.getText().toString()
                val userpass = pass.getText().toString()
                if (username.isEmpty()) {
                    Toast.makeText(
                        this@Flat_blank,
                        "Введите логин",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                if (userpass.isEmpty()) {
                    Toast.makeText(
                        this@Flat_blank,
                        "Введите пароль",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                else {
                    saveUserInfo(username, userpass)
                    startActivity(Intent(this@Flat_blank, CountPriceActivity::class.java))
                }
            }
            else
                Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show()
        })
    }

    private fun saveUserInfo(username: String, userpass: String) {
        val sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("userpass", userpass)
        editor.apply()
    }

    private fun getUserInfo(){
        val sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
        log.setText(sharedPreferences.getString("username", ""))
        pass.setText(sharedPreferences.getString("userpass", ""))
    }
}
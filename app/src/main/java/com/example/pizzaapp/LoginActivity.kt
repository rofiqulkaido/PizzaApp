package com.example.pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //hide title bar
        getSupportActionBar()?.hide()


        //instance text
        val txtUsername:EditText = findViewById(R.id.editTextEmail)
        val txtPassword:EditText = findViewById(R.id.editTextPassword)

        //instance button login
        val btnLogin: Button = findViewById(R.id.buttonLogin)
        val btnDontHave:TextView = findViewById(R.id.dontHave)


        //event button login
        btnLogin.setOnClickListener {
            val dbHelper = DatabaseHelper(this)

            //check data
            val data: String = dbHelper.checkData("stevi.ema@amikom.ac.id")
            Toast.makeText(this@LoginActivity, "Result : " + data, Toast.LENGTH_SHORT).show()
            if (data == "") {
                //insert data
                dbHelper.addAccount("stevi.ema@amikom.ac.id", "Stevi Ema W", "Cashier", "12345")
            }
            val email = txtUsername.text.toString().trim()
            val password = txtPassword.text.toString().trim()

            //check login
            val result: Boolean =
                dbHelper.checkLogin(txtUsername.text.toString(), txtPassword.text.toString())
            if (result) {
                val intentLogin = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intentLogin)
            } else {
                Toast.makeText(this, "Login Failed. Try Again", Toast.LENGTH_SHORT).show()
                txtUsername.hint = "email"
                txtPassword.hint = "password"
            }
        }

        //event button dont have
        btnDontHave.setOnClickListener {
            val intentRegister = Intent(this,RegisterActivity::class.java)
            startActivity(intentRegister)
        }
    }
}
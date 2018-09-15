package com.example.ehuang42.roommateapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
//import com.facebook.FacebookSdk;
//import com.facebook.appevents.AppEventsLogger;
//import com.facebook.login.widget.LoginButton


class MainActivity : AppCompatActivity() {


    val mAuth = FirebaseAuth.getInstance()
    val user  = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val loginBtn = findViewById<View>(R.id.login_btn) as Button
        val signupBtn = findViewById<View>(R.id.signup_btn)as Button
        loginBtn.setOnClickListener(View.OnClickListener {
            view -> login()
        })

        signup_btn.setOnClickListener(View.OnClickListener {
            view -> register()
        })

    }

    private fun register() {
        startActivity(Intent(this, register::class.java))
    }

    private fun login() {
        val emailTxt = findViewById<View>(R.id.username) as EditText
        val passwordTxt = findViewById<View>(R.id.password) as EditText
        var email = emailTxt.text.toString().trim()
        var password = passwordTxt.text.toString().trim()


        if (!email.isEmpty() && !password.isEmpty() && user!=null) {
            this.mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener (this, OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, Timeline::class.java))
                    Toast.makeText(this, "Successfully Logged in :)", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error Logging in :(", Toast.LENGTH_SHORT).show()
                }
            })

        } else {
            Toast.makeText(this, "Please fill up the Credentials :|", Toast.LENGTH_SHORT).show()
        }
    }
}

package com.hackmit.roommie

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
import com.google.firebase.auth.FirebaseUser
import com.hackmit.roommie.Controllers.QuestListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val mAuth = FirebaseAuth.getInstance()
    val user  = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val loginBtn = findViewById<View>(R.id.login_btn) as Button
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


        if (!email.isEmpty() && !password.isEmpty()) {
            this.mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener (this, OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, String.format("Logged in with %s.", emailTxt), Toast
                            .LENGTH_LONG)
                            .show()
                    startActivity(Intent(this, QuestListActivity::class.java))
                } else {
                    Toast.makeText(this, "Failed to log in. Please check your credentials!", Toast
                            .LENGTH_LONG).show()
                }
            })

        } else {
            Toast.makeText(this, "Please fill in your credentials!", Toast.LENGTH_LONG).show()
        }
    }
}

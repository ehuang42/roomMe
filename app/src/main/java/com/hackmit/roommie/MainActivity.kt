package com.hackmit.roommie

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.hackmit.roommie.Controllers.QuestListActivity
import com.hackmit.roommie.R.id.signup_btn
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
            if (rememberMeCheckBox.isChecked) {
                
            }
            this.mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener (this, OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, QuestListActivity::class.java))
                    Toast.makeText(this, "Successfully Logged in!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error Logging in, please check your credentials and " +
                            "try again later.", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "Please fill in your credentials", Toast.LENGTH_SHORT).show();
        }
    }
}

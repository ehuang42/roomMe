package com.hackmit.roommie

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class register : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    var db = FirebaseDatabase.getInstance()
    var dbRef = db.getReference("Users/User1/FirstName")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerBtn = findViewById<View>(R.id.signup_btn) as Button
        dbRef.addValueEventListener()



        registerBtn.setOnClickListener(View.OnClickListener {
            view -> registerUser()
        })

    }

    private fun registerUser () {
        val emailTxt = findViewById<View>(R.id.username) as EditText
        val passwordTxt = findViewById<View>(R.id.password) as EditText
        val nameTxt = findViewById<View>(R.id.username) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()
        var name = nameTxt.text.toString()

        if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    val uid = user!!.uid
                    mDatabase.child(uid).child("Name").setValue(name)
                    startActivity(Intent(this, Timeline::class.java))
                    Toast.makeText(this, "Successfully registered!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Cannot process your registration, please try again " +
                            "later.", Toast.LENGTH_LONG).show()
                }
            })
        } else {
            Toast.makeText(this,"Please fill in your credentials!", Toast.LENGTH_LONG).show()
        }
    }

}
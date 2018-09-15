package com.hackmit.roommie

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Timeline : AppCompatActivity() {

    lateinit var mDatabase : DatabaseReference
    var mAuth = FirebaseAuth.getInstance()
    var user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)
        //Tod-o: handle dataChange and onCancelled

//        val nameTxt = findViewById<View>(R.id.lastname) as TextView

        var uid = user!!.uid

//        mDatabase = FirebaseDatabase.getInstance().getReference("Names")
//
//        mDatabase.child(uid).child("Name").addValueEventListener(object : ValueEventListener{
//            override fun onCancelled(p0: DatabaseError) {
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                nameTxt.text =  "Welcome " + snapshot.value.toString()
//            }
//        })

    }



}
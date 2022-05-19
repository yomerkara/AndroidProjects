package com.feyzademirel.mobilequeries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    fun query1(view:View){
        val intent = Intent(this,Query1Activity::class.java)
        startActivity(intent)
    }
    fun query2(view:View){
        val intent = Intent(this,Query2Activity::class.java)
        startActivity(intent)
    }
    fun query3(view:View){
        val intent = Intent(this,Query3Activity::class.java)
        startActivity(intent)
    }
}
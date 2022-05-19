package com.feyzademirel.mobilequeries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Query3ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query3_result)
        val actionBar = supportActionBar
        actionBar!!.title = "QUERY 3 RESULT"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}
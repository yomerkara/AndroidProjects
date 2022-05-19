package com.example.buttoncounterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

private const val TAG = "MainActivity"
private const val TEXT_CONTENT = "Text Content"

class MainActivity : AppCompatActivity() {

    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate : called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userInput: EditText = findViewById<EditText>(R.id.editText)
        val button: Button = findViewById<Button>(R.id.button)
        textView = findViewById<TextView>(R.id.textView)
        textView?.text = ""
        textView?.movementMethod = ScrollingMovementMethod()

        userInput.text.clear()
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d(TAG, "onClick : called")
                textView?.append(userInput.text)
                textView?.append("\n")

                //userInput.text.clear()
                userInput.setText("")

            }
        })
    }

    override fun onStart() {
        Log.d(TAG, "onStart : called")
        super.onStart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(TAG, "onRestoreInstanceState : called")
        super.onRestoreInstanceState(savedInstanceState)
        textView?.text = savedInstanceState?.getString(TEXT_CONTENT, "")
    }

    override fun onResumeFragments() {
        Log.d(TAG, "onResume : called")
        super.onResumeFragments()
    }

    override fun onPause() {
        Log.d(TAG, "onPause : called")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "onSaveInstanceState : called")
        outState?.putString(TEXT_CONTENT, textView?.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        Log.d(TAG, "onStop : called")
        super.onStop()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart : called")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy : called")
        super.onDestroy()
    }
}
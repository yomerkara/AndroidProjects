package com.example.instagramclone

import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity

internal const val FLICKER_QUERY="FLICKR_QUERY"
internal const val PHOTO_TRANSFER="PHOTO_TRANSFER"


open class BaseActivity : AppCompatActivity(){
    private val TAG="BaseActivity"

    internal fun activateToolbar(enableHome:Boolean){
        Log.d(TAG,".activateToolbar")

        var toolbar=findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
    }
}
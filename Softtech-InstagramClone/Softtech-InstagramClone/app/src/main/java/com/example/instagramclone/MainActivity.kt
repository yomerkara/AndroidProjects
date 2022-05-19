package com.example.instagramclone

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Exception

class MainActivity : BaseActivity(), GetRawData.OnDownloadComplete,
    GetFlickerJsonData.OnDataAvalible,RecyclerItemClickListener.OnRecyclerClickListener {
    private val TAG = "MainActivity"

    private val flickrRecyclerViewAdapter = FlickerRecyclerViewAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activateToolbar(false)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addOnItemTouchListener(RecyclerItemClickListener(this,recycler_view,this))
        recycler_view.adapter = flickrRecyclerViewAdapter

        val url = createUri(
            "https://api.flickr.com/services/feeds/photos_public.gne",
            "android",
            "en-us",
            true
        )
        val getRawData = GetRawData(this)
        getRawData.execute(url)
        Log.d(TAG, "onCreate ends")
    }

    override fun onItemClick(view: View, position: Int) {
        Log.d(TAG,"onItemClick:starts")
        Toast.makeText(this,"Normal tap at position $position",Toast.LENGTH_SHORT).show()

    }

    override fun onItemLongClick(view: View, position: Int) {
        Log.d(TAG,"onItemLongClick:starts")
        //Toast.makeText(this,"Long tap at position $position",Toast.LENGTH_SHORT).show()

        val photo=flickrRecyclerViewAdapter.getPhoto(position)
        if(photo!=null){
            val intent=Intent(this,PhotoDetailsActivity::class.java)
            intent.putExtra(PHOTO_TRANSFER,photo)
            startActivity(intent)
        }
    }

    private fun createUri(
        baseUrl: String,
        searchCriteria: String,
        lang: String,
        matchAll: Boolean
    ): String {
        Log.d(TAG, "createUri starts")
        var uri = Uri.parse(baseUrl)
        var builder = uri.buildUpon()
        builder = builder.appendQueryParameter("tags", searchCriteria)
        builder = builder.appendQueryParameter("tagmode", if (matchAll) "ALL" else "ANY")
        builder = builder.appendQueryParameter("lang", lang)
        builder = builder.appendQueryParameter("format", "json")
        builder = builder.appendQueryParameter("nojsoncallback", "1")
        uri = builder.build()
        return uri.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d(TAG, "onCreateOptionsMenu called")
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d(TAG, "onOptionsItemSelected called")
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if (status == DownloadStatus.OK) {
            Log.d(TAG, "onDownloadComplete called")

            val getFlickerJsonData = GetFlickerJsonData(this)
            getFlickerJsonData.execute(data)
        } else {
            //download Failed
            Log.d(TAG, "onDownloadComplete failed with status $status.Error message is $$data")
        }
    }

    override fun onDataAvaliable(data: List<Photo>) {
        Log.d(TAG, "onDataAvailable called")
        flickrRecyclerViewAdapter.loadNewData(data)
        Log.d(TAG, "onDataAvailable ends")
    }

    override fun Error(exception: Exception) {
        Log.e(TAG, "onError called with ${exception.message}")
    }
}

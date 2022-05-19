@file:Suppress("DEPRECATION")

package com.example.top10downloaderapp

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import java.net.URL
import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.activity_main.xmlListView

class FeedEntry{
    var name:String=""
    var artist:String=""
    var releaseDate:String=""
    var summary:String=""
    var imageURL:String=""
    override fun toString(): String {
        return """
            name=$name
            artist=$artist
            releaseDate=$releaseDate
            summary=$summary
            imageURL=$imageURL
            """.trimIndent()
    }
}
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var downloadData:DownloadData? = null
    private var feedURL="http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=%d/xml"
    private var feedLimit =10

    private var feedCacheURL = "INVALIDATED"
    private val STATE_URL="feedURL"
    private val STATE_LIMIT="feedLimit"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG,"onCrate:called")
        if(savedInstanceState!=null){
            feedURL= savedInstanceState.getString(STATE_URL).toString()
            feedLimit=savedInstanceState.getInt(STATE_LIMIT)
        }
        downloadUrl(feedURL.format(feedLimit))
        Log.d(TAG,"onCreate: done")
    }

    private fun downloadUrl(feedURL:String){
        if(feedURL!=feedCacheURL){
            Log.d(TAG, "downloadURL Starting AsyncTask")
            downloadData= DownloadData(this,xmlListView)
            downloadData?.execute(feedURL)
            feedCacheURL=feedURL
            Log.d(TAG, "downloadURL done")
        }else{
            Log.d(TAG,"downloadURL not changed")

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.feeds_menu,menu)
        if(feedLimit==10){
            menu?.findItem(R.id.mnu10)?.isChecked=true
        }else{
            menu?.findItem(R.id.mnu25)?.isChecked=true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when(item?.itemId){
            R.id.mnuFree ->
                feedURL="http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=%d/xml"
            R.id.mnuPaid ->
                feedURL="http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/toppaidapplications/limit=%d/xml"
            R.id.mnuSongs ->
                feedURL="http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=%d/xml"
            R.id.mnu10 ,R.id.mnu25->{
                if(!item.isChecked){
                    item.isChecked=true
                    feedLimit=35-feedLimit
                    Log.d(TAG,"onOptionsItemSelected : ${item.title} setting feed limit to $feedLimit")
                }else{
                    Log.d(TAG,"onOptionsItemSelected : ${item.title} setting feed limit unchanged")
                }
            }
             R.id.mnuRefresh -> feedCacheURL="INVALIDATED"

            else ->
                return super.onOptionsItemSelected(item)
        }
        downloadUrl(feedURL.format(feedLimit))
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_URL,feedURL)
        outState.putInt(STATE_LIMIT,feedLimit)
    }

    override fun onDestroy() {
        super.onDestroy()
        downloadData?.cancel(true)
    }

    companion object {
        private class DownloadData(context:Context,listView: ListView) : AsyncTask<String, Void, String>() {
            private val TAG  = "DownloadData"

            var propContext:Context by Delegates.notNull()
            var propListView:ListView by Delegates.notNull()

            init {
                propContext=context
                propListView=listView
            }
            override fun doInBackground(vararg urls: String?): String {
                Log.d(TAG, "doInBackground starts with : ${urls[0]}")

                val rssFeed = downloadXML(urls[0])
                if (rssFeed.isEmpty()) {
                    Log.e(TAG, "doInBackground error downloading")
                }
                return rssFeed
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                //Log.d(TAG, "onPostExecute : parameter is $result")
                val parseApplications=ParseApplications()
                parseApplications.parse(result)

//                val arrayAdapter=ArrayAdapter<FeedEntry>(propContext,R.layout.list_item,parseApplications.applications)
 //               propListView.adapter=arrayAdapter
                val feedAdapter=FeedAdapter(propContext,R.layout.list_record,parseApplications.applications)
                propListView.adapter=feedAdapter
            }

            private fun downloadXML(urlPath: String?): String {
//                val xmlResult = StringBuilder()
//
//                try {
//                    val url = URL(urlPath)
//                    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
//                    val response = connection.responseCode
//
//                    Log.d(TAG, "downloadXML: The response code was :$response")
//
////            val inputStream = connection.inputStream
////            val inputStreamReader = InputStreamReader(inputStream)
////            val reader = BufferedReader(inputStreamReader)
////            val reader = BufferedReader(InputStreamReader(connection.inputStream))
////            xmlResult.append(reader.readText())
////            reader.close()
////                    val stream=connection.inputStream
//                    connection.inputStream.bufferedReader().use {
//                        xmlResult.append(it.readText())
//                    }
//                    Log.d(TAG, "Received ${xmlResult.length} bytes")
//                    return xmlResult.toString()
////                } catch (e: MalformedURLException) {
////                    Log.e(TAG, "downloadXML : invalid URL ${e.message}")
////                } catch (e: IOException) {
////                    Log.e(TAG, "downloadXML : IO exception reading data: ${e.message}")
////                } catch (e: SecurityException) {
////                    e.printStackTrace()
////                    Log.e(TAG, "downloadXML : Security exception.Permission denied ?:${e.message}")
////                } catch (e: Exception) {
////                    Log.e(TAG, "Unknown Error :${e.message}")
////                }
//                } catch (e: Exception) {
//
//
//                    val errorMessage: String = when (e) {
//                        is MalformedURLException -> "downloadXML : invalid URL ${e.message}"
//                        is IOException -> "downloadXML : IO exception reading data: ${e.message}"
//                        is SecurityException -> {
//                            e.printStackTrace()
//                            "downloadXML : Security Exception. Permission denied ? :${e.message}"
//                        }
//                        else -> "Unknown Error: ${e.message}"
//                    }
//                }
//                return "" //If it gets ot here,there's been a problem. Return empty String.

                return URL(urlPath).readText()
            }
        }
    }

}
@file:Suppress("DEPRECATION")

package com.example.top10downloader

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import java.io.IOException
//import java.lang.Exception
//import java.net.HttpURLConnection
//import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate : called")
        val downloadData = DownloadData()
        downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
        Log.d(TAG, "onCreate :done")
    }

    companion object {
        private class DownloadData : AsyncTask<String, Void, String>() {
            private val TAG = "DownloadData"
            override fun doInBackground(vararg url: String?): String {
                Log.d(TAG, "doInBackground: starts with ${url[0]}")
                val rssFeed = downloadXML(url[0])
                if (rssFeed.isEmpty()) {
                    Log.e(TAG, "doInBackground : Error downloading")
                }
                return rssFeed
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                Log.d(TAG, "onPostExecute : parameter is $result")
            }

            private fun downloadXML(urlPath: String?): String {
                return URL(urlPath).readText()
//                val xmlResult = StringBuilder()
//                try {
//                    val url = URL(urlPath)
//                    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
//                    val response = connection.responseCode
//
//                    Log.d(TAG, "downloadXML : The response was $response")
//
////            val inputStream=connection.inputStream
////            val inputStreamReader=InputStreamReader(inputStream)
////            val reader=BufferedReader(inputStreamReader)
//
//
////                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
////                    val inputBuffer = CharArray(500)
////                    var charsRead = 0
////                    while (charsRead >= 0) {
////                        charsRead = reader.read(inputBuffer)
////                        if (charsRead > 0) {
////                            xmlResult.append(String(inputBuffer, 0, charsRead))
////                        }
////                    }
////                    reader.close()
//
////                    val stream =connection.inputStream
//                    connection.inputStream.buffered().reader().use{
//                        xmlResult.append(it.readText())
//                    }
//
//
//                    Log.d(TAG, "Received : ${xmlResult.length}")
//                    return xmlResult.toString()
//
////                } catch (e: MalformedURLException) {
////                    Log.e(TAG, "downloadXML : Invalid URL ${e.message}")
////                } catch (e: IOException) {
////                    Log.e(TAG, "downloadXML : IO Exception reading data :${e.message}")
////                }catch (e : SecurityException){
////                    e.printStackTrace()
////                    Log.e(TAG ,"downloadXML : Security exception . Needs permission ? ${e.message}")
////                } catch (e: Exception) {
////                    Log.e(TAG, "Unknown exception ${e.message}")
////                }
//                }
//                catch (e : Exception){
//                    val errorMessage: String = when(e){
//                        is MalformedURLException -> "downloadXML : Invalid URL ${e.message}"
//                        is IOException -> "downloadXML : IO Exception reading data :${e.message}"
//                        is SecurityException ->{
//                            e.printStackTrace()
//                            "downloadXML : Security exception . Needs permission ? ${e.message}"}
//                        else -> "Unknown Error"
//                    }
//                }
//                return "" //here a problem
            }
        }
    }


}
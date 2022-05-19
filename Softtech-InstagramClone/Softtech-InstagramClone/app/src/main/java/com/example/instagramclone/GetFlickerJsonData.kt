package com.example.instagramclone

import android.os.AsyncTask
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class GetFlickerJsonData(private val listener:OnDataAvalible) : AsyncTask<String,Void,ArrayList<Photo>>() {

    private val TAG="GetFlickerJsonData"

    interface OnDataAvalible{
        fun onDataAvaliable(data : List<Photo>)
        fun Error(exception: Exception)
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun doInBackground(vararg params: String?): ArrayList<Photo> {
        Log.d(TAG,"doInBackground starts")
        val photoList=ArrayList<Photo>()

        try {
            val jsonData=JSONObject(params[0])
            val itemsArray=jsonData.getJSONArray("items")
            for(i in 0 until itemsArray.length()){
                val jsonPhoto=itemsArray.getJSONObject(i)

                val title=jsonPhoto.getString("title")
                val author=jsonPhoto.getString("author")
                val authorID=jsonPhoto.getString("author_id")
                val tags=jsonPhoto.getString("tags")

                val jsonMedia=jsonPhoto.getJSONObject("media")
                val photoUrl=jsonMedia.getString("m")
                val link=photoUrl.replaceFirst("_m.jpg","_b.jpg")

                val photoObject=Photo(title,author,authorID,link,tags,photoUrl)

                photoList.add(photoObject)
                Log.d(TAG,"doInBackground $photoObject")
            }
        }catch (e:JSONException){
            e.printStackTrace()
            Log.d(TAG,"doInBackground :Error processing Json data ${e.message}")
            cancel(true)
            listener.Error(e)
        }

        Log.d(TAG,"doInBackground ends")
        return photoList
    }

    override fun onPostExecute(result: ArrayList<Photo>) {
        Log.d(TAG,"doInBackground starts")
        super.onPostExecute(result)
        listener.onDataAvaliable(result)
        Log.d(TAG,"onPostExecute ends")
    }
}
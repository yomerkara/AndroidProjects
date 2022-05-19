package com.feyzademirel.mobilequeries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_query1.*
import kotlinx.android.synthetic.main.activity_query3.*
import kotlinx.android.synthetic.main.activity_query3_result.*

class Query3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query3)
        val actionBar = supportActionBar
        actionBar!!.title = "QUERY 3"
        actionBar.setDisplayHomeAsUpEnabled(true)


    }
    fun query3result(view: View){
        var top5distance = hashMapOf<String,Double>()
        var date1 = tarih1.text.toString()
        var date2 = tarih2.text.toString()
        var sayac=0
        var bitis=70
        var baslangic =0
        var sayac2=0
        var database = FirebaseDatabase.getInstance().reference
        var getdata = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    var date = i.child("tpep_pickup_datetime").getValue().toString()
                    sayac++
                    if((date.contains(date1, ignoreCase = true))==true){
                        baslangic=sayac
                    } else if((date.contains(date2, ignoreCase = true))==true){
                        bitis=sayac
                    }
                }
                for (j in snapshot.children){
                    sayac2++
                    if(sayac2>=baslangic && sayac2 <=bitis){
                        var distance = j.child("trip_distance").getValue().toString()
                        var date = j.child("tpep_pickup_datetime").getValue().toString()
                        var dist: Double? = distance.toDoubleOrNull()
                        if (dist != null ) {
                            top5distance.put(date,dist)

                        }
                    }

                }
                val sortedMapDist = top5distance.toList().sortedBy { (k, v) -> v }.toMap()
                sortedMapDist.forEach { (k, v) -> println("$k => $v") }
                val keyList = ArrayList(sortedMapDist.keys)
                val valueList = ArrayList(sortedMapDist.values)

                sonuc1.setText("1. "+keyList.get(0) + " => " + valueList.get(0))
                sonuc2.setText("2. "+keyList.get(1) + " => " + valueList.get(1))
                sonuc3.setText("3. "+keyList.get(2) + " => " + valueList.get(2))
                sonuc4.setText("4. "+keyList.get(3) + " => " + valueList.get(4))
                sonuc5.setText("5. "+keyList.get(4) + " => " + valueList.get(4))

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)
    }
}
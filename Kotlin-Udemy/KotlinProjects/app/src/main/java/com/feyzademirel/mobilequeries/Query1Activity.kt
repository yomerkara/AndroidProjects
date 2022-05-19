package com.feyzademirel.mobilequeries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_query1.*
import java.lang.StringBuilder

class Query1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_query1)
        val actionBar = supportActionBar
        actionBar!!.title = "QUERY 1"
        actionBar.setDisplayHomeAsUpEnabled(true)


        var top5days = hashMapOf<String,Int>()


        ///database
        var a1=0
        var a2=0
        var a3=0
        var a4=0
        var a5=0
        var a6=0
        var a7=0
        var a8=0
        var a9=0
        var a10=0
        var a11=0
        var a12=0
        var a13=0
        var a14=0
        var a15=0
        var a16=0


        var database =FirebaseDatabase.getInstance().reference
        var getdata = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    var date = i.child("tpep_pickup_datetime").getValue().toString()
                    var passenger = i.child("passenger_count").getValue().toString()
                    if((date.contains("2020-12-01", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a1+=int1
                        }
                   } else if((date.contains("2020-12-02", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a2+=int1
                        }
                    }
                    else if((date.contains("2020-12-03", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a3+=int1
                        }
                    }else if((date.contains("2020-12-04", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a4+=int1
                        }
                    }
                    else if((date.contains("2020-12-05", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a5+=int1
                        }
                    }else if((date.contains("2020-12-06", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a6+=int1
                        }
                    }else if((date.contains("2020-12-07", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a7+=int1
                        }
                    }else if((date.contains("2020-12-08", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a8+=int1
                        }
                    }else if((date.contains("2020-12-09", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a9+=int1
                        }
                    }else if((date.contains("2020-12-10", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a10+=int1
                        }
                    }
                    else if((date.contains("2020-12-11", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a11+=int1
                        }
                    }else if((date.contains("2020-12-12", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a12+=int1
                        }
                    }else if((date.contains("2020-12-13", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a13+=int1
                        }
                    }else if((date.contains("2020-12-14", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a14+=int1
                        }
                    }else if((date.contains("2020-12-15", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a15+=int1
                        }
                    }else if((date.contains("2020-12-16", ignoreCase = true))==true){
                        var int1: Int? = passenger.toIntOrNull()
                        if (int1 != null) {
                            a16+=int1
                        }
                    }
                }
                //query1result.setText(sb)
               // println(sb)
                top5days.put("2020-12-01", a1)
                top5days.put("2020-12-02", a2)
                top5days.put("2020-12-03", a3)
                top5days.put("2020-12-04", a4)
                top5days.put("2020-12-05", a5)
                top5days.put("2020-12-06", a6)
                top5days.put("2020-12-07", a7)
                top5days.put("2020-12-08", a8)
                top5days.put("2020-12-09", a9)
                top5days.put("2020-12-10", a10)
                top5days.put("2020-12-11", a11)
                top5days.put("2020-12-12", a12)
                top5days.put("2020-12-13", a13)
                top5days.put("2020-12-14", a14)
                top5days.put("2020-12-15", a15)
                top5days.put("2020-12-16", a16)
                val sortedMap = top5days.toList().sortedBy { (k, v) -> v }.toMap()
                sortedMap.forEach { (k, v) -> println("$k => $v") }

                val keyList = ArrayList(sortedMap.keys)
                val valueList = ArrayList(sortedMap.values)
               /* for (x in 1..10){
                    keyList.removeAt(0)
                    valueList.removeAt(0)
                }*/

                top1.setText("1. "+keyList.get(15) + " => " + valueList.get(15))
                top2.setText("2. "+keyList.get(14) + " => " + valueList.get(14))
                top3.setText("3. "+keyList.get(13) + " => " + valueList.get(13))
                top4.setText("4. "+keyList.get(12) + " => " + valueList.get(12))
                top5.setText("5. "+keyList.get(11) + " => " + valueList.get(11))
                println(a2 + a16)
               // query1result.setText(top5days["2020-12-01"].toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }


        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)

    }
}

package com.feyzademirel.mobilequeries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_query2.*

class Query2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query2)
        val actionBar = supportActionBar
        actionBar!!.title = "QUERY 2"
        actionBar.setDisplayHomeAsUpEnabled(true)

}
    fun okeyClick(view: View) {
        var date_1 = date1.text.toString()
        var date_2 = date2.text.toString()
        var lokasyon = loc.text.toString()
        var arac_sayisi=0
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
                    if((date.contains(date_1, ignoreCase = true))==true){
                        baslangic=sayac
                    } else if((date.contains(date_2, ignoreCase = true))==true){
                        bitis=sayac
                    }

                }
                for (j in snapshot.children){
                    sayac2++
                    if(sayac2>=baslangic && sayac2 <=bitis){
                        var location = j.child("PULocationID").getValue().toString()
                        if((location.contains(lokasyon, ignoreCase = true))==true){
                            arac_sayisi++
                        }

                    }

                }

                println(arac_sayisi)
                result.setText("Toplam Araç Sayısı: "+arac_sayisi.toString())

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)

    }

}

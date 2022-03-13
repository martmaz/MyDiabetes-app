package com.example.mydiabetes

import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent1 = Intent(this, Pomiar::class.java)
        buttonPomiar.setOnClickListener {
            startActivity(intent1)
        }

        var intent2 = Intent(this, Statystyki::class.java)
        buttonStatystyki.setOnClickListener {
            startActivity(intent2)
        }

        var intetnt3 = Intent(this, Notatki::class.java)
        buttonNotatki.setOnClickListener {
            startActivity(intetnt3)
        }



    }

}
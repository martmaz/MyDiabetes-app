package com.example.mydiabetes

import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main1.*
import java.time.LocalDate
import java.time.LocalDateTime
import android.provider.BaseColumns
import android.widget.Toast
import android.database.sqlite.SQLiteDatabase
import  android.database.sqlite.SQLiteOpenHelper
import android.widget.CursorTreeAdapter
import kotlinx.android.synthetic.main.card_view.*
import java.text.SimpleDateFormat
import java.util.*


class Pomiar: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)


        val db = openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null)
       db.execSQL("CREATE TABLE IF NOT EXISTS Pomiary(Data VARCHAR(10), Pomiar INT, Posilek VARCHAR(15), Waga INT)"
       )
        // val db = dbHelper.writableDatabase


        if(intent.hasExtra("Data")) Data_cardView.setText(intent.getStringExtra("Data"))
        if(intent.hasExtra("Pomiar")) Pomiar_cardView.setText(intent.getStringExtra("Pomiar"))
        if(intent.hasExtra("Posilek")) Posilek_cardView.setText(intent.getStringExtra("Posilek"))
        if(intent.hasExtra("Wagaa")) Waga_cardView.setText(intent.getStringExtra("Waga"))



//--------------------zapis danych----------------
        buttonDodajPom.setOnClickListener {

            val Data = editTextDate.text.toString()
            val Pomiar = editTextPomiar.text.toString()
            val Waga = editTextNumberWaga.text.toString()
            var Posilek: String = ""

               if (radioButtonPrzedPosiłkiem.isChecked) {
                     Posilek = "Przed Posiłkiem"
                } else if (radioButtonPoPosiłku.isChecked) {
                     Posilek = "Po posiłku"

                } else {
                     Posilek = ""
                }



           if(Data.isNullOrEmpty() || Pomiar.isNullOrEmpty() ||Posilek.isNullOrEmpty()) {
               Toast.makeText(applicationContext, "Nie podano wszystkich danych!", Toast.LENGTH_LONG).show()
            }
           else
            {
                val value = ContentValues()
                value.put("Data", Data)
                value.put("Pomiar", Pomiar)
                value.put("Posilek", Posilek)
                value.put("Waga", Waga)

                db.insert("Pomiary", null, value)
            }
        }
        //db.close()
        }
    }


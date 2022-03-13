package com.example.mydiabetes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.list_view.*
import kotlin.math.roundToInt

class Lista:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view)


        val db = openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null)
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS Pomiary(Data VARCHAR(10), Pomiar INT, Posilek VARCHAR(15), Waga INT)"
        )


        val myCursor = db.rawQuery("select Data, Pomiar, Posilek, Waga from Pomiary", null)
        while (myCursor.moveToNext()) {
                val Data = myCursor.getString(0)
                val Pomiar = myCursor.getInt(1)
                var Posilek = myCursor.getString(2)
                val Waga = myCursor.getInt(3)



        println("data: $Data pomiar: $Pomiar, Posilek: $Posilek  waga:$Waga")


        //val adapter = CardViewAdapter(this, db)

            //------------------ustawienie widoku listy----------------

            //var layoutManager = GridLayoutManager(this, 2)  //siatka
            var layoutManager = LinearLayoutManager(this)  //lista
            recyler_View.layoutManager = layoutManager
            recyler_View.adapter = CardViewAdapter(applicationContext, db)


    }
        myCursor.close()

    }


}





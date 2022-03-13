package com.example.mydiabetes

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main1.view.*
import kotlinx.android.synthetic.main.card_view.view.*

class CardViewAdapter(val context: Context, val db: SQLiteDatabase) : RecyclerView.Adapter<MyViewHolder>(){


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
       val layoutInflater = LayoutInflater.from(p0.context)
       val cardView_pomiary = layoutInflater.inflate(R.layout.card_view, p0, false)
       return MyViewHolder(cardView_pomiary)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        //-----elementy pojedynczego pomiaru--------

        var cardView_pomiary = holder.view.pomiary_cardView
        var Data = holder.view.Data_cardView
        var Pomiar = holder.view.Pomiar_cardView
        var Posilek = holder.view.Posilek_cardView
        var Waga = holder.view.Waga_cardView
        var context = holder.view.context


        // holder.Data_cardView.text = db.toString()[position].toString()
        // val dane : Pomiar = myArray[position]
        //holder.Data_cardView.text = dane.toString()


        //-----------wczytywanie tresci------
       val myCursor = db.rawQuery("select Data, Pomiar, Posilek, Waga from Pomiary",  null   //, arrayOf(holder.adapterPosition.plus(1).toString()
        )
       if(myCursor.moveToFirst()) {
          do {
               Data.setText(myCursor.getString(0))
               Pomiar.setText(myCursor.getString(1))
               Posilek.setText(myCursor.getString(2))
               Waga.setText(myCursor.getString(3))
           } while (myCursor.moveToNext())
       }



        //.........edycja danych z listy..........

            val intent_edit = Intent(context, Pomiar::class.java)
        cardView_pomiary.setOnClickListener {
            val Data_edit = Data.text.toString()
            val Pomiar_edit = Pomiar.text.toString()
            val Posilek_edit = Posilek.text.toString()
            val Waga_edit = Waga.text.toString()
            //val id_edit = holder.adapterPosition.plus(1).toString()

            intent_edit.putExtra("Data", Data_edit)
            intent_edit.putExtra("Pomiar", Pomiar_edit)
            intent_edit.putExtra("Posilek", Posilek_edit)
            intent_edit.putExtra("Waga", Waga_edit)
            //intent_edit.putExtra("Id", id_edit)

            context.startActivity(intent_edit)
        }

    }


        //-----wczytywanie tresci------
       /* val cursor = db.query("Pomiary", null,
            BaseColumns._ID + "=?", arrayOf(holder.adapterPosition.plus(1).toString()),
            null, null, null, null)

        if (cursor.moveToFirst()) {
            if(!(cursor.getString(1).isNullOrEmpty() &&
                 cursor.getString(2).isNullOrEmpty() &&
                cursor.getString(3).isNullOrEmpty() &&
                cursor.getString(4).isNullOrEmpty())) {

                Data.setText(cursor.getString(position))
                Pomiar.setText(cursor.getInt(position))
                Posilek.setText(cursor.getString(position))
                Waga.setText(cursor.getInt(position))
                //---------------------
            }
        }
         */

    override fun getItemCount(): Int {
        val myCursor = db.rawQuery("select Data, Pomiar, Posilek, Waga from Pomiary", null)
        val liczbawierszy = myCursor.count
        myCursor.close()
        return liczbawierszy


    }

}
class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)





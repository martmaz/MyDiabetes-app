package com.example.mydiabetes

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main1.*
import kotlinx.android.synthetic.main.activity_main2.*
import kotlin.collections.MutableMap.MutableEntry
import kotlin.math.roundToInt


class Statystyki : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val db = openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null)
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS Pomiary(Data VARCHAR(10), Pomiar INT, Posilek VARCHAR(15), Waga INT)"
        )


        var listData = ArrayList<String>()
        var listPomiar = mutableListOf<Int>()
        var listPosilek = mutableListOf<String>()
        var listWaga = mutableListOf<Int>()
        val myCursor = db.rawQuery("select Data, Pomiar, Posilek, Waga from Pomiary", null)

        while (myCursor.moveToNext()) {
            val Data = myCursor.getString(0)
            var Pomiar = myCursor.getInt(1)
            var Posilek = myCursor.getString(2)
            val Waga = myCursor.getInt(3)
            listData.add(Data)
            listPomiar.add(Pomiar)
            listPosilek.add(Posilek)
            listWaga.add(Waga)


            //val intent = Intent(this, Pomiar::class.java)
            // intent.putExtra("Pomiar", editTextPomiar.text.toString())
            // startActivity(intent)


            var Norma = mutableListOf<Int>()
            var PowNormy = mutableListOf<Int>()
            var PonNormy = mutableListOf<Int>()

            for (i in 0..(listPomiar.count() - 1)) {
                if (listPomiar[i] == 70) {
                    PonNormy.add(i)
                }
                if (listPomiar[i] < 70) {
                    PonNormy.add(i)
                }
                if (listPomiar[i] > 99) {
                    PowNormy.add(i)
                } else {
                    Norma.add(i)
                }
            }


            textViewPonNormy.text = "         ${PonNormy.count()}" + "\n" + "Pnożej normy"
            textViewWNormie.text = "       ${Norma.count()}" + "\n" + "W normie"
            textViewZPowNormy.text = "         ${PowNormy.count()}" + "\n" + "Powyżej normy"

            //----------ostatni pomiar-------------

            textViewOstatniPomiar.text =
                "   dn. $Data  pomiar: $Pomiar mg/dl - $Posilek"

            //---------sreni pomiar----------

            var PomiarAvg = ((listPomiar.sum().toDouble()) / (listPomiar.count())).roundToInt()
            textViewPomiarAVG.text = "   Średni pomiar:        $PomiarAvg mg/dl"
            //println("Sr pomiar: $PomiarAvg")
            //println(listPomiar)

            //---------srednia waga-------------

            var WagaAvg = ((listWaga.sum().toDouble()) / (listWaga.count())).roundToInt()
            textViewSrWaga.text = "   Średnia waga:        $WagaAvg kg"

        }



        var intent5 = Intent(this, ChartPrzedPosilkiem::class.java)
        buttonChart1.setOnClickListener{
            startActivity(intent5)
        }

        var intent6 = Intent(this, ChartPoPosilku::class.java)
        buttonChart2.setOnClickListener{
            startActivity(intent6)
        }


            var intetnt4 = Intent(this, Lista::class.java)
            buttonBazaPomiarow.setOnClickListener {
                startActivity(intetnt4)
            }




        var listPrzedPosilkiem = mutableListOf<Int>()
        var listPoPosilku = mutableListOf<Int>()

        for(i in 0..(listPosilek.count()-1))
        {
            if (listPosilek[i] == "Przed Posiłkiem" )
            {
                    listPrzedPosilkiem.add(listPomiar[i])

            }
            else if(listPosilek[i] == "Po posiłku") {
                listPoPosilku.add(listPomiar[i])

            }
        }
        println(listPosilek)
        println(listPoPosilku)
        println(listPrzedPosilkiem)


        /*
               //---------------------wykres--------------------
               var mChart = findViewById<View>(R.id.chart);



               var values: ArrayList<Entry> = ArrayList<Entry>()
               var e1: Entry


               if (listPomiar.count() > 30) {
                   for (i in (listPomiar.count() - 20)..(listPomiar.count() - 1)) {
                       e1 = Entry(i.toFloat(), listPomiar[i].toFloat())
                       values.add(e1)
                   }
               } else if (listPomiar.count() > 0) {
                   for (i in 0..(listPomiar.count() - 1)) {
                       e1 = Entry(i.toFloat(), listPomiar[i].toFloat())
                       values.add(e1)
                   }
               }
               else
               {
                   e1 = Entry(0.toFloat(),0.toFloat())
                   values.add(e1)
               }

                   if (listPoPosilku.count() > 30) {
                       for (i in (listPoPosilku.count() - 20)..(listPoPosilku.count() - 1)) {
                           e1 = Entry(i.toFloat(), listPoPosilku[i].toFloat())
                           values.add(e1)
                       }
                   } else if (listPoPosilku.count() > 0) {
                       for (i in 0..(listPoPosilku.count() - 1)) {
                           e1 = Entry(i.toFloat(), listPoPosilku[i].toFloat())
                           values.add(e1)
                       }
                   } else {
                       e1 = Entry(0.toFloat(), 0.toFloat())
                       values.add(e1)
                   }


                   if (listPrzedPosilkiem.count() > 30) {
                       for (i in (listPrzedPosilkiem.count() - 20)..(listPrzedPosilkiem.count() - 1)) {
                           e1 = Entry(i.toFloat(), listPrzedPosilkiem[i].toFloat())
                           values.add(e1)
                       }
                   } else if (listPrzedPosilkiem.count() > 0) {
                       for (i in 0..(listPrzedPosilkiem.count() - 1)) {
                           e1 = Entry(i.toFloat(), listPrzedPosilkiem[i].toFloat())
                           values.add(e1)
                       }
                   } else {
                       e1 = Entry(0.toFloat(), 0.toFloat())
                       values.add(e1)
                   }
               }


               var set1:LineDataSet = LineDataSet(values,"Wartości pomiarów")
               set1.setDrawIcons(false)
               set1.enableDashedLine(10f, 5f, 0f)
               set1.setColor(Color.CYAN)
               set1.setCircleColor(Color.CYAN)
               set1.lineWidth = 1f
               set1.circleHoleRadius = 3f
               set1.setDrawCircleHole(false)
               set1.valueTextSize = 9f
               set1.setDrawFilled(true)
               set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
               set1.formSize = 15f
               set1.fillColor = Color.WHITE

               var dataSets: ArrayList<LineDataSet> = ArrayList()
               dataSets.add(set1)

               var data : LineData = LineData(dataSets as List<ILineDataSet>?)
               chart.data = data
               chart.setDrawGridBackground(false)
               chart.description = null

               chart.notifyDataSetChanged()

       */

//-----------------------------------------
    }
}
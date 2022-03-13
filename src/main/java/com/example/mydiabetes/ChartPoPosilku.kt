package com.example.mydiabetes

import android.content.Context
import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_chart2.*
import kotlinx.android.synthetic.main.activity_main2.*

class ChartPoPosilku : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart2)



        val db = openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null)
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS Pomiary(Data VARCHAR(10), Pomiar INT, Posilek VARCHAR(15), Waga INT)"
        )


        var listData = mutableListOf<String>()
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


        //------------WYKRES PO POSILKU---------------

        var Chart = findViewById<View>(R.id.chartt)

        var values: ArrayList<Entry> = ArrayList<Entry>()
        var e2: Entry


            for (i in 0..(listPoPosilku.count()-1)){
                e2 = Entry(i.toFloat(), listPoPosilku[i].toFloat())
                values.add(e2)
            }



        var set2: LineDataSet = LineDataSet(values,"Wartości pomiarów")
        set2.setDrawIcons(false)
        set2.enableDashedLine(10f, 5f, 0f)
        set2.setColor(Color.CYAN)
        set2.setCircleColor(Color.CYAN)
        set2.lineWidth = 1f
        set2.circleHoleRadius = 3f
        set2.setDrawCircleHole(false)
        set2.valueTextSize = 9f
        set2.setDrawFilled(true)
        set2.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
        set2.formSize = 15f
        set2.fillColor = Color.WHITE

        var dataSets: ArrayList<LineDataSet> = ArrayList()
        dataSets.add(set2)

        var data : LineData = LineData(dataSets as List<ILineDataSet>?)
        chartt.data = data
        chartt.setDrawGridBackground(false)
        chartt.description = null

        chartt.notifyDataSetChanged()


    }
}
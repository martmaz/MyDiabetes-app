package com.example.mydiabetes

import android.content.Context
import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_chart1.*
import kotlinx.android.synthetic.main.activity_main2.*

class ChartPrzedPosilkiem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart1)


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


        //----------WYKRES PRZED POSILKIEM-----------------------------
        var mChart = findViewById<View>(R.id.chart1)


        var values: ArrayList<Entry> = ArrayList<Entry>()
        var e1: Entry

       //if (listPrzedPosilkiem.count() > 50) {
       //     for (i in (listPrzedPosilkiem.count() - 40)..(listPrzedPosilkiem.count() - 1)) {
        for(i in 0..(listPrzedPosilkiem.count()-1)){
                e1 = Entry(i.toFloat(), listPrzedPosilkiem[i].toFloat())
                values.add(e1)
            }
      /*} else if (listPrzedPosilkiem.count() > 0) {
            for (i in 0..(listPrzedPosilkiem.count() - 1)) {
                e1 = Entry(i.toFloat(), listPrzedPosilkiem[i].toFloat())
                values.add(e1)
            }
       } else {
            e1 = Entry(0.toFloat(), 0.toFloat())
            values.add(e1)
        }

       */



        var set1: LineDataSet = LineDataSet(values,"Wartości pomiarów")
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
        chart1.data = data
        chart1.setDrawGridBackground(false)
        chart1.description = null

        chart1.notifyDataSetChanged()

        var start = 0
        var stop = listPrzedPosilkiem.count()

      //  ---------------suwak do przesuwania wykresu-------------
/*
        seekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress != null) {
                    var floatVal = 1.0f + progress * 0.8f
                    System.out.println("Progress #3: val " + floatVal)
                    chart1.setScaleX(floatVal)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    stop = seekBar.progress
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    start = seekBar.progress

                }
            }
        })
 */

    }
}
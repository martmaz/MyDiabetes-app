package com.example.mydiabetes

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main3.*

class Notatki:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        var listNotatka = mutableListOf<String>()



        /*val db = openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS Notatki(Notatka VARCHAR(100))"
        )

*/
        buttonDodajNotke.setOnClickListener {
            var Notatka = editTextWpiszNotke.text.toString()


            if (Notatka.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "Podaj treść notatki!", Toast.LENGTH_LONG).show()
            } else {
                val value = ContentValues()
                value.put("Notatkaa", Notatka)
                listNotatka.add(Notatka)

                fun WyswietNotatki(): String {
                    return "$Notatka" + "\n"
                }
                textViewListaNotatek.text = ""
                listNotatka.forEach() {
                    textViewListaNotatek.append(WyswietNotatki())

                    // db.insert("Pomiary", null, value)
                }
            }


            // val myCursor = db.rawQuery("select Notatka from Pomiary", null)
            // val Notatka = myCursor.getString(0)


        }


           // myCursor.close()



           /* for(i in 0..(listNotatka.count()-1))
            {
                textViewListaNotatek.text = listNotatka[i] + "\n"
            }
            */

            //textViewListaNotatek.text = "$listNotatka"
        println(listNotatka)


    }
}

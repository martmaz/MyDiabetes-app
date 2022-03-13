//package com.example.mydiabetes

import  android.content.Context
import android.database.sqlite.SQLiteDatabase
import  android.database.sqlite.SQLiteOpenHelper
import  android.provider.BaseColumns
/*
object TableDane : BaseColumns{

    //----opis tabeli-----
    const val TABLE_NAME = "Moje Pomiary"
    const val TABLE_COLUMN_DATA = "Data"
    const val TABLE_COLUMN_POMIAR = "Pomiar"
    const val TABLE_COLUMN_POSILEK = "Posilek"
    const val TABLE_COLUMN_WAGA = "Waga"
}
//----------------


//------Podstawowe komendy SQL------
object BasicCommand {
    const val SQL_CREATE_TABLE : String
            = "CREATE TABLE ${TableDane.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${TableDane.TABLE_COLUMN_DATA} TEXT NOT NULL," +
            "${TableDane.TABLE_COLUMN_POMIAR} TEXT NOT NULL," +
            "${TableDane.TABLE_COLUMN_POSILEK} TEXT NOT NULL," +
            "${TableDane.TABLE_COLUMN_WAGA} TEXT NOT NULL,"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${TableDane.TABLE_NAME}"
}
//------------------

class DataBaseHelper (context: Context) : SQLiteOpenHelper(context, TableDane.TABLE_NAME, null, 1 ) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE)
        onCreate(db)
    }
}

*/
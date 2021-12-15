package com.example.sqllite_28_10_21.dataBase

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.sqllite_28_10_21.dataBase.Aplicacion.Companion.BASE
import com.example.sqllite_28_10_21.dataBase.Aplicacion.Companion.VERSION
import com.example.sqllite_28_10_21.dataBase.Aplicacion.Companion.appContext

class MyDatabaseHelper: SQLiteOpenHelper(appContext, BASE, null, VERSION) {
    val crearTabla="CREATE TABLE ${Aplicacion.TABLA} (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "nombre TEXT NOT NULL, email TEXT NOT NULL, " + "imagen TEXT NOT NULL);"
    override fun onCreate(p0: SQLiteDatabase?) {
        Log.d("BASE DE DATOS >>>>>>>>>>>", crearTabla)
        p0?.execSQL(crearTabla)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val drop= "DROP TABLE IF EXISTS ${Aplicacion.TABLA}"
        p0?.execSQL(drop)
        onCreate(p0)
    }
}
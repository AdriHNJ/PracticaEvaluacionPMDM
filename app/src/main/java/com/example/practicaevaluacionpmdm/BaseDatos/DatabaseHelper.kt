package com.example.practicaevaluacionpmdm.BaseDatos

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.practicaevaluacionpmdm.BaseDatos.Aplicacion.Companion.BASE
import com.example.practicaevaluacionpmdm.BaseDatos.Aplicacion.Companion.VERSION
import com.example.practicaevaluacionpmdm.BaseDatos.Aplicacion.Companion.appContext

class DatabaseHelper: SQLiteOpenHelper(appContext, BASE, null, VERSION) {
    //vamos a crear una cadena en la que pondremos la query necesaria para crear la base de datos que queremos
    val crearTabla="CREATE TABLE ${Aplicacion.TABLA} (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "origen TEXT NOT NULL, destino TEXT NOT NULL, " + "distancia INTEGER NOT NULL);"
    override fun onCreate(p0: SQLiteDatabase?){
        //Ejecutamos la query que hemos creado anteriormente
        Log.d("BASE DE DATOS >>>>>>>>>>>", crearTabla)
        p0?.execSQL(crearTabla)
    }

    //Cuando actualizemos
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //Creamos una query para que se elimine la base de datos si ya exite y la ejecutamos
        val drop= "DROP TABLE IF EXISTS ${Aplicacion.TABLA}"
        p0?.execSQL(drop)
        onCreate(p0)
    }
}

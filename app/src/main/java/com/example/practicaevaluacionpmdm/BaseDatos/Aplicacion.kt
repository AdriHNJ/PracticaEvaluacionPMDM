package com.example.practicaevaluacionpmdm.BaseDatos

import android.app.Application
import android.content.Context
import android.os.Build

class Aplicacion: Application(){
    companion object{
        //Creamos las variables necesarias para crear la base de datos
        val BASE = "baseViajes"
        val TABLA = "tablaViajes"
        val VERSION = 1
        lateinit var appContext: Context
        //Creamos un databaseHelper, la clase estará en otro fichero
        lateinit var databaseHelper:DatabaseHelper
    }

    override fun onCreate() {
        super.onCreate()
        appContext=applicationContext
        databaseHelper= DatabaseHelper()
    }
}
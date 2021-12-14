package com.example.sqllite_28_10_21.dataBase

import android.app.Application
import android.content.Context

class Aplicacion: Application() {
    companion object{
        val BASE = "baseContactos"
        val TABLA = "tablaContactos"
        val VERSION = 1
        lateinit var appContext: Context
        lateinit var DB:MyDatabaseHelper
    }

    override fun onCreate() {
        super.onCreate()
        appContext=applicationContext
        DB=MyDatabaseHelper()
    }
}
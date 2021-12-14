package com.example.practicaevaluacionpmdm.BaseDatos

import android.database.DatabaseUtils
import android.util.Log
import com.example.practicaevaluacionpmdm.recycler.DatosViajes
import java.lang.Exception

class AdministrarViajes {
    //Esta función crea una query y la inserta en nuestra base de datos
    fun crearViaje(viaje: DatosViajes){
        val insertar="INSERT INTO ${Aplicacion.TABLA}(origen, destino, distancia) values('${viaje.origen}', '${viaje.destino}', '${viaje.distancia}');"
        //1*. Crea la conexcion en modo escritura o lectura, en este caso escritura
        val conexion = Aplicacion.databaseHelper.writableDatabase
        try {
            conexion.execSQL(insertar)
            conexion.close()
        } catch (ex: Exception){
            Log.d("Error al insertar:::",ex.message.toString())
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------
//Esta funcion lee todos los registros y los muestra en el recyclerView
    fun readAll(): MutableList<DatosViajes>{
        //Creamos una lista de tipo datos viajes
        val lista = mutableListOf<DatosViajes>()
        //Creamos la query para leer los datos y mostrarlos por origen
        val consulta = "select * from ${Aplicacion.TABLA} order by origen"
        //Nos conectamos leyendo la base de datos
        val conexion = Aplicacion.databaseHelper.readableDatabase
        val total = DatabaseUtils.queryNumEntries(conexion, Aplicacion.TABLA)
        //Mostramos los registros siempre que haya
        if (total>0){
            try {
                val cursor = conexion.rawQuery(consulta, null)
                while (cursor.moveToNext()){
                    var item = DatosViajes(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3))
                    lista.add(item)
                }
                conexion.close()
            }catch (ex: Exception){
                Log.d("Error al recuperar registros:::",ex.message.toString())
            }
        }
        return lista
    }
//------------------------------------------------------------------------------------------------------------------------------------
    //Esta funcion se encarga de borrar un registro recibiendo su id como parámetro
    fun borrar(id: Int){
        val query = "DELETE FROM ${Aplicacion.TABLA} WHERE ID = $id"
        val conexion = Aplicacion.databaseHelper.writableDatabase
        try {
            conexion.execSQL(query)
            conexion.close()
        } catch (ex: Exception){
            Log.d("ERROR:::","ERROR AL BORRAR::: "+ ex.message.toString())
        }
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------
    //Esta función se encarga de actualizar un regitro de la base de datos cuando le pasemos una id
    fun update(c: DatosViajes){
        val query = "UPDATE ${Aplicacion.TABLA} SET origen='${c.origen}', destino='${c.destino}', distancia='${c.distancia}' WHERE id = ${c.id}"
        val conexion = Aplicacion.databaseHelper.writableDatabase
        try {
            conexion.execSQL(query)
            conexion.close()
        } catch (ex: Exception){
            Log.d("ERROR:::","ERROR AL ACTUALIZAR "+ ex.message.toString())
        }
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------
    //Esta funcion borra toda la base de datos
    fun borrarTodo(){
        val q = "DELETE FROM ${Aplicacion.TABLA}"
        val conexion = Aplicacion.databaseHelper.writableDatabase
        try {
            conexion.execSQL(q)
            conexion.close()
        } catch (ex: Exception){
            Log.d("ERROR:::","ERROR AL ACTUALIZAR "+ ex.message.toString())
        }
    }
}
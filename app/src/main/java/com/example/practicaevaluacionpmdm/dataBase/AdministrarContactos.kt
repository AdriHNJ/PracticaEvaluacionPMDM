package com.example.sqllite_28_10_21.dataBase

import android.database.DatabaseUtils
import android.util.Log
import com.example.sqllite_28_10_21.recycler.DatosContactos
import java.lang.Exception

class AdministrarContactos {
    fun crearContacto(contacto: DatosContactos){
        val insertar="INSERT INTO ${Aplicacion.TABLA}(nombre, email, imagen) values('${contacto.nombre}', '${contacto.email}', '${contacto.imagen}');"
        //1*. Crea la conexcion en modo escritura o lectura, en este caso escritura
        val conexion = Aplicacion.DB.writableDatabase
        try {
            conexion.execSQL(insertar)
            conexion.close()
        } catch (ex: Exception){
            Log.d("Error al insertar:::",ex.message.toString())
        }
    }
    //-----------------------------------------------------------------------------------------------------------------------------
    fun readAll(): MutableList<DatosContactos>{
        val lista = mutableListOf<DatosContactos>()
        val consulta = "select * from ${Aplicacion.TABLA} order by nombre"
        val conexion = Aplicacion.DB.readableDatabase
        val total = DatabaseUtils.queryNumEntries(conexion, Aplicacion.TABLA)
        if (total>0){
            try {
                val cursor = conexion.rawQuery(consulta, null)
                while (cursor.moveToNext()){
                    var item = DatosContactos(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3))
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
    fun borrar(id: Int){
        val query = "DELETE FROM ${Aplicacion.TABLA} WHERE ID = $id"
        val conexion = Aplicacion.DB.writableDatabase
        try {
            conexion.execSQL(query)
            conexion.close()
        } catch (ex: Exception){
            Log.d("ERROR:::","ERROR AL BORRAR::: "+ ex.message.toString())
        }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------
    fun update(c: DatosContactos){
        val query = "UPDATE ${Aplicacion.TABLA} SET nombre='${c.nombre}', email='${c.email}', imagen='${c.imagen}' WHERE id = ${c.id}"
        val conexion = Aplicacion.DB.writableDatabase
        try {
            conexion.execSQL(query)
            conexion.close()
        } catch (ex: Exception){
            Log.d("ERROR:::","ERROR AL ACTUALIZAR "+ ex.message.toString())
        }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------
    fun borrarTodo(){
        val q = "DELETE FROM ${Aplicacion.TABLA}"
        val conexion = Aplicacion.DB.writableDatabase
        try {
            conexion.execSQL(q)
            conexion.close()
        } catch (ex: Exception){
            Log.d("ERROR:::","ERROR AL ACTUALIZAR "+ ex.message.toString())
    }
    }
}
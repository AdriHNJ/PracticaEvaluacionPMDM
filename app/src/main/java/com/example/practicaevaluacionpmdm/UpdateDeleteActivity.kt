package com.example.practicaevaluacionpmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.practicaevaluacionpmdm.databinding.ActivityUpdateeDeleteBinding
import com.example.sqllite_28_10_21.dataBase.AdministrarContactos
import com.example.sqllite_28_10_21.recycler.DatosContactos

class UpdateDeleteActivity : AppCompatActivity() {
    var nombre = ""
    var email = ""
    var id = 0
    lateinit var binding: ActivityUpdateeDeleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdateeDeleteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        cogerDatos()
        setListeners()
    }
    //-----------------------------------------------------------------------------------------------
    private fun setListeners() {
        binding.btnVolver.setOnClickListener {
            onBackPressed()
        }
        binding.btnBorrar.setOnClickListener {
            borrar()
        }
        binding.btnUpdate.setOnClickListener {
            actualizar()
        }
    }
    //-----------------------------------------------------------------------------------------------
    private fun actualizar() {

        nombre = binding.etUpdelNombre.text.toString().trim()
        val email = binding.etUpdelMail.text.toString().trim()
        if (nombre.length<3){
            binding.etUpdelNombre.setError("El nombre debe tener al menos 3 letras!!!")
            return
        }
        if (email.isEmpty()){
            binding.etUpdelMail.setError("Rellene este campo!!!")
            return
        }

        val imagen="https://via.placeholder.com/150/0000FF/FFFFFF?text="+nombre.substring(0,3).uppercase()
        val esteContacto = DatosContactos(id, nombre, email, imagen)
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Actualizar contacto")
            .setMessage("Se actualiazará el contacto")
            .setPositiveButton("OK", null)
            .create()
            .show()
        AdministrarContactos().update(esteContacto)
        Toast.makeText(this, "Contacto Actualizado", Toast.LENGTH_LONG). show()
        onBackPressed()
    }

    //-----------------------------------------------------------------------------------------------
    private fun borrar() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Alerta Borrar")
            .setMessage("¿Desea borrar permanentemente el contacto?")
            .setNegativeButton("Cancelar"){v,_ -> v.dismiss() }
            //SNB("texto"){p1, p2 -> lo que queremos hacer}
            .setPositiveButton("Aceptar"){_,_->
                AdministrarContactos().borrar(id)
                onBackPressed()
            }
            .setCancelable(false)
            .create()
            .show()
    }

    //-----------------------------------------------------------------------------------------------
    private fun cogerDatos() {
        val bundle = intent.extras
        nombre = bundle?.getString("NOMBRE").toString()
        email = bundle?.getString("EMAIL").toString()
        id = bundle?.getInt("ID", 0)!!
        binding.etUpdelNombre.setText(nombre)
        binding.etUpdelMail.setText(email)
    }
}
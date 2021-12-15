package com.example.practicaevaluacionpmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.practicaevaluacionpmdm.databinding.ActivityAddBinding
import com.example.sqllite_28_10_21.dataBase.AdministrarContactos
import com.example.sqllite_28_10_21.recycler.DatosContactos

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListernersBotones()
    }

    private fun setListernersBotones() {
        binding.btnLimpiar.setOnClickListener{
            binding.etAddNombre.setText("")
            binding.etAddEmail.setText("")
            binding.etAddNombre.requestFocus()
        }
        binding.btnGuardar.setOnClickListener{
            guardarContacto()
        }
    }

    private fun guardarContacto() {
        val nombre = binding.etAddNombre.text.toString().trim()
        val email = binding.etAddEmail.text.toString().trim()
        if (nombre.length<3){
            binding.etAddNombre.setError("El nombre debe tener al menos 3 letras!!!")
            return
        }
        if (email.isEmpty()){
            binding.etAddEmail.setError("Rellene este campo!!!")
            return
        }
        val imagen="https://via.placeholder.com/150/0000FF/FFFFFF?text="+nombre.substring(0,3).uppercase()
        val esteContacto = DatosContactos(1, nombre, email, imagen)
        val ac= AdministrarContactos()
        ac.crearContacto(esteContacto)
        Toast.makeText(this, "Contacto Guardado.", Toast.LENGTH_LONG).show()
        onBackPressed()
    }
}
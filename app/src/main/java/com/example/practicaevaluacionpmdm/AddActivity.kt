package com.example.practicaevaluacionpmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.practicaevaluacionpmdm.BaseDatos.AdministrarViajes
import com.example.practicaevaluacionpmdm.databinding.ActivityAddBinding
import com.example.practicaevaluacionpmdm.recycler.DatosViajes

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.btnLimpiar.setOnClickListener{
            binding.etAddOrigen.setText("")
            binding.etAddDestino.setText("")
            binding.etAddDistancia.setText("")
            binding.etAddOrigen.requestFocus()
        }
        binding.btnGuardar.setOnClickListener{
            guardarContacto()
        }
    }

    private fun guardarContacto() {
        val origen = binding.etAddOrigen.text.toString().trim()
        val destino = binding.etAddDestino.text.toString().trim()
        val distancia = binding.etAddDistancia.text.toString().trim()
        if (origen.isEmpty()){
            binding.etAddOrigen.setError("El nombre origen no puede estar vacío")
            return
        }
        if (destino.isEmpty()){
            binding.etAddDestino.setError("Rellene este campo!!!")
            return
        }
        if (distancia.isEmpty()){
            binding.etAddDistancia.setError("Rellene este campo!!!")
            return
        }
        val esteContacto = DatosViajes(1, origen, destino, distancia.toInt())
        val ac=AdministrarViajes()
        ac.crearViaje(esteContacto)
        Toast.makeText(this, "Viaje Guardado.", Toast.LENGTH_LONG).show()
        onBackPressed()
    }
}
package com.example.practicaevaluacionpmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.practicaevaluacionpmdm.BaseDatos.AdministrarViajes
import com.example.practicaevaluacionpmdm.databinding.ActivityUpdateDeleteBinding
import com.example.practicaevaluacionpmdm.recycler.DatosViajes

class UpdateDeleteActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateDeleteBinding
    var origen = ""
    var destino = ""
    var distancia = 0
    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdateDeleteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        cogerDatos()
        setListeners()
    }

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

    private fun actualizar() {
        origen = binding.etUpdelOrigen.text.toString().trim()
        val destino = binding.etUpdelDestino.text.toString().trim()
        val distancia = binding.etUpdelDistancia.text.toString().trim()
        if (origen.isEmpty()){
            binding.etUpdelOrigen.setError("Rellene este campo!!!")
            return
        }
        if (destino.isEmpty()){
            binding.etUpdelDestino.setError("Rellene este campo!!!")
            return
        }
        if (distancia.isEmpty()){
            binding.etUpdelOrigen.setError("Rellene este campo!!!")
            return
        }
        val esteViaje = DatosViajes(id, origen, destino, distancia.toInt())
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Alerta Actualizar")
            .setMessage("¿Desea actualizar el contacto?")
            .setNegativeButton("Cancelar"){v,_ -> v.dismiss() }
            //SNB("texto"){p1, p2 -> lo que queremos hacer}
            .setPositiveButton("Aceptar"){_,_->
                AdministrarViajes().update(esteViaje)
                Toast.makeText(this, "Viaje Actualizado", Toast.LENGTH_LONG).show()
                onBackPressed()
            }
            .setCancelable(false)
            .create()
            .show()
    }

    private fun borrar() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Alerta Borrar")
            .setMessage("¿Desea borrar permanentemente el contacto?")
            .setNegativeButton("Cancelar"){v,_ -> v.dismiss() }
            .setPositiveButton("Aceptar"){_,_->
                AdministrarViajes().borrar(id)
                onBackPressed()
            }
            .setCancelable(false)
            .create()
            .show()
    }

    private fun cogerDatos() {
        val bundle = intent.extras
        origen = bundle?.getString("ORIGEN").toString()
        destino = bundle?.getString("DESTINO").toString()
        distancia = bundle?.getInt("DISTANCIA", 0)!!
        id = bundle?.getInt("ID", 0)!!
        binding.etUpdelOrigen.setText(origen)
        binding.etUpdelDestino.setText(destino)
        binding.etUpdelDistancia.setText(distancia.toString())
    }
}
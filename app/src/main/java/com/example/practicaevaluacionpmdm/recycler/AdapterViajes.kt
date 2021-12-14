package com.example.practicaevaluacionpmdm.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaevaluacionpmdm.R
import com.example.practicaevaluacionpmdm.UpdateDeleteActivity
import com.example.practicaevaluacionpmdm.databinding.ViajesLayoutBinding

class AdapterViajes(private val lista:MutableList<DatosViajes>): RecyclerView.Adapter<AdapterViajes.ViajesHolder>() {
    lateinit var context: Context
    //Creamos un holder apuntando a el layout del cardView
    class ViajesHolder (v:View): RecyclerView.ViewHolder(v) {
        val binding = ViajesLayoutBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViajesHolder {
        //Pasamos un contexto del padre
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.viajes_layout, parent, false)
        return ViajesHolder(v)
    }

    override fun onBindViewHolder(holder: ViajesHolder, position: Int) {
        var elemento = lista[position]
        holder.binding.tvOrigen.text=elemento.origen
        holder.binding.tvDestino.text=elemento.destino
        holder.binding.tvDistancia.text=elemento.distancia.toString()
        holder.binding.tvId.text=elemento.id.toString()
        holder.binding.cardView.setOnClickListener {
            val i = Intent(context, UpdateDeleteActivity::class.java).apply {
                putExtra("ORIGEN", elemento.origen)
                putExtra("DESTINO", elemento.destino)
                putExtra("DISTANCIA", elemento.distancia)
                putExtra("ID", elemento.id)
            }
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return lista.count()
    }
}
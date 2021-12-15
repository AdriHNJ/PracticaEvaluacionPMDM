package com.example.sqllite_28_10_21.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaevaluacionpmdm.R
import com.example.practicaevaluacionpmdm.UpdateDeleteActivity
import com.example.practicaevaluacionpmdm.databinding.ContactosLayoutBinding


class AdapterContactos(private val lista: MutableList<DatosContactos>): RecyclerView.Adapter<AdapterContactos.ContactosHolder>() {
    lateinit var context: Context

    class ContactosHolder (v:View): RecyclerView.ViewHolder(v){
        val binding = ContactosLayoutBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactosHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.contactos_layout, parent, false)
        return ContactosHolder(v)
    }

    override fun onBindViewHolder(holder: ContactosHolder, position: Int) {
        val elemento = lista[position]
        holder.binding.tvNombre.text=elemento.nombre
        holder.binding.tvEmail.text=elemento.email
        holder.binding.tvId.text=elemento.id.toString()
        holder.binding.cardView.setOnClickListener{
            val i = Intent(context, UpdateDeleteActivity::class.java).apply {
                putExtra("NOMBRE", elemento.nombre)
                putExtra("EMAIL", elemento.email)
                putExtra("ID", elemento.id)
            }
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return lista.count()
    }
}
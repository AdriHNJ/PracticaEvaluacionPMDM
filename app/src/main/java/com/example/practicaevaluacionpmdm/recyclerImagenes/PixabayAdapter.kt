package com.example.practicaevaluacionpmdm.recyclerImagenes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabay_08_11_21.api.PixaGson
import com.example.practicaevaluacionpmdm.R
import com.example.practicaevaluacionpmdm.databinding.ActivityPixabayAdapterBinding
import com.squareup.picasso.Picasso

class PixabayAdapter(private var lista: List<PixaGson>) : RecyclerView.Adapter<PixabayAdapter.ViewHolder>() {
    class ViewHolder(v :View): RecyclerView.ViewHolder(v) {
        val binding = ActivityPixabayAdapterBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.activity_pixabay_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.binding.tvAutor.text = item.user.toString()
        holder.binding.tvLikes.text = item.likes.toString()
        holder.binding.tvDescargas.text = item.descargas.toString()
        holder.binding.tvVisitas.text = item.visitas.toString()
        Picasso.get().load(item.imagen)
            .resize(170,150)
            .centerCrop()
            .into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return lista.count()
    }

}
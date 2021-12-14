package com.example.practicaevaluacionpmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaevaluacionpmdm.databinding.ActivityBaseBinding
import com.example.sqllite_28_10_21.dataBase.AdministrarContactos
import com.example.sqllite_28_10_21.recycler.AdapterContactos
import com.example.sqllite_28_10_21.recycler.DatosContactos

class BaseActivity : AppCompatActivity() {
    lateinit var binding: ActivityBaseBinding
    lateinit var adapter: AdapterContactos
    lateinit var lista: MutableList<DatosContactos>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lista = mutableListOf()
        setBtnAdd()
        setRecycler()
    }
    //--------------------------------------------------------------------------------------------
    private fun setRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        rellenarLista()
        adapter = AdapterContactos(lista)
        binding.recyclerView.adapter=adapter
    }
    //-----------------------------------------------------------------------------------------------
    private fun rellenarLista() {
        val ac = AdministrarContactos()
        lista = ac.readAll()
    }
    //-----------------------------------------------------------------------------------------------
    private fun setBtnAdd() {
        binding.btnAdd.setOnClickListener{
            addContact()
        }
    }
    //-----------------------------------------------------------------------------------------------
    private fun addContact() {
        val i = Intent(this, AddActivity::class.java)
        startActivity(i)
    }
    //-----------------------------------------------------------------------------------------------
    override fun onRestart() {
        super.onRestart()
        setRecycler()
    }
    //-----------------------------------------------------------------------------------------------
    override fun onResume() {
        super.onResume()
        setRecycler()
    }
    //Pintamos el menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_opciones, menu)
        return super.onCreateOptionsMenu(menu)
    }
//Ponemos listeners a las opciones de menu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.item_salir->{
                finishAndRemoveTask()
            }
            else -> {
                AdministrarContactos().borrarTodo()
                setRecycler()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
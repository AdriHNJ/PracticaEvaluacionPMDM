package com.example.practicaevaluacionpmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.practicaevaluacionpmdm.Maps.MapsActivity
import com.example.practicaevaluacionpmdm.WebView.WebViewActivity
import com.example.practicaevaluacionpmdm.databinding.ActivityAppBinding
import com.google.firebase.auth.FirebaseAuth

enum class Providers{
    BASIC,
    GOOGLE,
    INVITED
}
class AppActivity : AppCompatActivity() {
    var email=""
    var provider=""
    lateinit var prefs: Prefs
    lateinit var binding: ActivityAppBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAppBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        prefs = Prefs(this)
        cogerDatos()
        ponerListeners()
        guardarDatos()
    }

    private fun guardarDatos() {
        prefs.guardarEmail(email)
        prefs.guardarProvider(provider)
    }

    private fun ponerListeners() {
        binding.btnLogout.setOnClickListener {
            cerrarSesion()
        }
        binding.btnMapa.setOnClickListener{
            val i = Intent(this, MapsActivity::class.java)
            startActivity(i)
        }
        binding.btnNavegador.setOnClickListener {
            val i = Intent(this, WebViewActivity::class.java)
            startActivity(i)
        }
    }

    private fun cerrarSesion() {
        FirebaseAuth.getInstance().signOut()
        prefs.borraTodo()
        onBackPressed()
    }

    private fun cogerDatos() {
        val bundle = intent.extras

        email = bundle?.getString("EMAIL").toString()
        provider = bundle?.getString("PROVIDER").toString()

        binding.txtEmail.text=email
        binding.txtProvider.text=provider
    }
}
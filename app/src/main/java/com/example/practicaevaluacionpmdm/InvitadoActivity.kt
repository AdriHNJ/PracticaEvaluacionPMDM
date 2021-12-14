package com.example.practicaevaluacionpmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicaevaluacionpmdm.databinding.ActivityInvitadoBinding
import com.google.firebase.auth.FirebaseAuth

enum class Providers1{
    BASIC,
    GOOGLE,
    INVITED
}
class InvitadoActivity : AppCompatActivity() {
    var provider=""
    private lateinit var prefs: Prefs
    lateinit var binding: ActivityInvitadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInvitadoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        prefs = Prefs(this)
        cogerDatos()
        ponerListeners()
        guardarDatos()
    }

    private fun guardarDatos() {
        prefs.guardarProvider(provider)
    }

    private fun ponerListeners() {
        binding.btnLogout.setOnClickListener {
            cerrarSesion()
        }
    }

    private fun cerrarSesion() {
        FirebaseAuth.getInstance().signOut()
        prefs.borraTodo()
        onBackPressed()
    }

    private fun cogerDatos() {
        val bundle = intent.extras

        provider = bundle?.getString("PROVIDER").toString()
        binding.txtProvider2.text=provider
    }
}
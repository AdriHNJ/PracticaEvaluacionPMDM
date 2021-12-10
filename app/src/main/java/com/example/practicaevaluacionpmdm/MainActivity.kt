package com.example.practicaevaluacionpmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.practicaevaluacionpmdm.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var email=""
    var pass=""
    lateinit var prefs: Prefs
    private val responseLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode== RESULT_OK){
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val cuenta = task.getResult(ApiException::class.java)
                    if (cuenta!=null){
                        val credenciales = GoogleAuthProvider.getCredential(cuenta.idToken, null)
                        FirebaseAuth.getInstance().signInWithCredential(credenciales).addOnCompleteListener{
                            if (it.isSuccessful){
                                irApp(cuenta.email?:"", Providers.GOOGLE)
                            } else {
                                mostrarError()
                            }
                        }
                    }
                } catch (e: ApiException){
                    Log.d("ERROR------>>>>>>>>>>>>>>>>>>>>>>>>>>", e.message.toString())
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = Prefs(this)
        setUp()
        comprobarSesion()
    }

    private fun comprobarSesion() {
        val e= prefs.leerEmail()
        val p=prefs.leerProvider()

        if (!e.isNullOrEmpty() && !p.isNullOrEmpty()){
            irApp(e, Providers.valueOf(p))
        }
    }

    private fun setUp() {
        title="Autenticación"
        binding.btnRegistrar.setOnClickListener {
            registrar()
        }
        binding.btnAcceder.setOnClickListener {
            acceder()
        }
        binding.btnGoogle.setOnClickListener {
            accederConGoogle()
        }
    }

    private fun accederConGoogle() {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("929030850461-n1eusapsrdsttcg51kab0isv8rniq833.apps.googleusercontent.com")
            .requestEmail()
            .build()
        val googleClient = GoogleSignIn.getClient(this, gso)
        //lanzamos el activity con las cuentas de google disponibles
        googleClient.signOut()
        responseLauncher.launch(googleClient.signInIntent)
    }

    private fun acceder() {
        if (!cogerDatos()) return
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful){
                irApp(it.result?.user?.email?:"", Providers.BASIC)
            } else {
                mostrarError()
            }
        }
    }

    private fun registrar() {
        if (!cogerDatos()) return
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful){
                //el registro ha ido bien
                irApp(it.result?.user?.email?:"", Providers.BASIC)
            } else {
                mostrarError()
            }
        }
    }

    private fun mostrarError() {
        val alerta = AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Se ha producido un error")
            .setPositiveButton("Aceptar", null)
            .create()
            .show()
    }

    private fun irApp(email: String, provider: Providers) {
        limpiar()
        val i = Intent(this, AppActivity::class.java).apply {
            putExtra("EMAIL", email)
            putExtra("PROVIDER", provider.name)
        }
        startActivity(i)
    }



    private fun limpiar() {
        binding.etEmail.setText("")
        binding.etPassword.setText("")
        binding.etEmail.requestFocus()
    }


    private fun cogerDatos(): Boolean {
        email=binding.etEmail.text.toString().trim()
        pass=binding.etPassword.text.toString().trim()
        if (email.length==0){
            binding.etEmail.setError("Rellene el campo email!")
            return false
        }
        if (pass.length==0){
            binding.etPassword.setError("Rellene el campo password")
            return false
        }
        return true
    }

}

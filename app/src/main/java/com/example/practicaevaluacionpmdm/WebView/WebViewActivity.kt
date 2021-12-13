package com.example.practicaevaluacionpmdm.WebView

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.webkit.*
import android.widget.SearchView
import androidx.core.view.isVisible
import com.example.practicaevaluacionpmdm.R
import com.example.practicaevaluacionpmdm.databinding.ActivityAppBinding
import com.example.practicaevaluacionpmdm.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityWebViewBinding
    val URL_INICIO="https://www.google.es"
    val SEARCH="/search?q="
    val URL_GOOGLE = "https://www.google.com/"
    val URL_MOODLE_ALMERIA = "https://educacionadistancia.juntadeandalucia.es/centros/almeria/"
    val URL_KOTLIN = "https://kotlinlang.org/docs/home.html"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        colocarListeners()
        colocarWebView()
    }

    private fun colocarWebView() {
        binding.webView.webChromeClient = object : WebChromeClient(){

        }
        binding.webView.webViewClient = object  : WebViewClient(){
            //Modificaremos su comportamiento por defecto para solucionar el problema de la flecha de carga

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                //return super.shouldOverrideUrlLoading(view, request)
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.swipeRefresh.isRefreshing = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.swipeRefresh.isRefreshing = false
            }
        }
        //Activamos javascript, por defecto desactivado
        val settings = binding.webView.settings
        settings.javaScriptEnabled = true
        //-----
        binding.webView.loadUrl(URL_INICIO)
    }

    private fun colocarListeners() {
        //1-- Listener del swipeRefresh
        binding.swipeRefresh.setOnRefreshListener {
            binding.webView.reload() //Si dejamos esto, la rueda de carga permanecerá
        }
        //2-- Search_View
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //p0 es lo que hemos escrito en la barra de busqueda
                p0?.let {
                    if(URLUtil.isValidUrl(it)){
                        binding.webView.loadUrl(it)
                    } else {
                        binding.webView.loadUrl("$URL_INICIO$SEARCH$it")
                    }
                }
                ocultarTeclado()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
        //3-- Botones
        binding.btnIrgoogle.setOnClickListener {
            binding.webView.loadUrl(URL_GOOGLE)
        }
        binding.btnIrmoodle.setOnClickListener {
            binding.webView.loadUrl(URL_MOODLE_ALMERIA)
        }
        binding.btnIrkotlin.setOnClickListener {
            binding.webView.loadUrl(URL_KOTLIN)
        }
        binding.btnSalirNavegador.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        //Para poder ir navegando por todas las paginas vistas
        if(binding.webView.canGoBack()){
            binding.webView.goBack()
        } else{
            super.onBackPressed()
        }

    }

    private fun ocultarTeclado() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.vistaRoot.windowToken, 0)
    }
}
package com.example.pixabay_08_11_21.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET //Los datos nos llegan por get
    suspend fun getDatosPixaBay(@Url url: String): Response<ListaPixaGson>
    //suspend es porque a esta funcion la llamaremos desde
    //otro hilo, no del principal. En este caso utilizaremos coroutine
}
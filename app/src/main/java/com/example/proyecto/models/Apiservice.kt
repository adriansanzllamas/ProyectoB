package com.example.proyecto.models

import retrofit2.Call
import retrofit2.http.GET

interface Apiservice {
    @GET("facturas")
    fun getAllFacturas(): Call <FacturaResponse>


}
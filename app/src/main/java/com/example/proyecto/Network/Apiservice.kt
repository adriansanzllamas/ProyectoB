package com.example.proyecto.Network

import com.example.proyecto.models.InvoiceResponseVO
import retrofit2.Call
import retrofit2.http.GET

interface Apiservice {
    @GET("facturas")
    fun getAllFacturas(): Call <InvoiceResponseVO>


}
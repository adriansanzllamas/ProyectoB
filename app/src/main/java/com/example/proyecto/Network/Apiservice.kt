package com.example.proyecto.Network


import com.example.proyecto.models.InvoiceResponseVO
import com.example.proyecto.models.InvoiceVO
import retrofit2.Call
import retrofit2.http.GET

interface Apiservice {
    @GET("facturas")
    fun getAllFacturas(): Call <InvoiceResponseVO>


}
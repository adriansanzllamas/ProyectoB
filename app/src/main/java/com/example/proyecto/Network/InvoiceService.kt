package com.example.proyecto.Network


import com.example.proyecto.models.InvoiceVO
import retrofit2.Response
import java.io.IOException


class InvoiceService {
    private val retrofit: RetrofitHelper

    @get:Throws(IOException::class)
    val invoices: Response<InvoiceVO>
        get() {
            val apiService: Apiservice =
                retrofit.getRetrofit().create(Apiservice::class.java)
            val response: Response<InvoiceVO>
            response = apiService.getAllFacturas().execute()
            return response

        }


    init {
        retrofit = RetrofitHelper()
    }
}
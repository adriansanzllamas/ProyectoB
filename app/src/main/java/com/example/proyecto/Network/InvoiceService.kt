package com.example.proyecto.Network


import com.example.proyecto.models.InvoiceResponseVO
import retrofit2.Response
import java.io.IOException


class InvoiceService {
    private val retrofit: RetrofitHelper

    @get:Throws(IOException::class)
    val invoices: Response<InvoiceResponseVO>
        get() {
            val apiService: Apiservice =
                retrofit.getRetrofit().create(Apiservice::class.java)
            val response: Response<InvoiceResponseVO>
            response = apiService.getAllFacturas().execute()
            return response

        }


    init {
        retrofit = RetrofitHelper()
    }
}
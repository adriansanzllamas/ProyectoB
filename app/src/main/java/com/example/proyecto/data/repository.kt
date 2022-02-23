package com.example.proyecto.data

import com.example.proyecto.Network.Apiservice
import com.example.proyecto.Network.InvoiceService
import com.example.proyecto.Network.RetrofitHelper
import com.example.proyecto.data.models.FacturaProvider
import com.example.proyecto.data.models.InvoiceResponseVO
import com.example.proyecto.data.models.InvoiceVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class repository {


    suspend fun getAllFacturas():InvoiceResponseVO?{
// esta seroia como nuestra pequeña base de datos nuestro buck -end
        val call =
            RetrofitHelper().getRetrofit().create(Apiservice::class.java).getAllFacturas()
                .execute()
        val factura: InvoiceResponseVO? = call.body()
        var Listadatos = mutableListOf<InvoiceResponseVO?>()
        Listadatos.addAll(listOf(factura))
        // las facturas se las pasamos a nuestra Facturaprovider.
        FacturaProvider.facturas=Listadatos
        return factura

    }
}
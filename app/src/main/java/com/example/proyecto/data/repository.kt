package com.example.proyecto.data

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.Network.Apiservice
import com.example.proyecto.Network.RetrofitHelper
import com.example.proyecto.data.models.FacturaProvider
import com.example.proyecto.data.models.InvoiceResponseVO
import com.example.proyecto.data.models.InvoiceVO
import com.example.proyecto.databinding.ActivityMainBinding
import com.example.proyecto.ui.view.FacturaHolder
import com.example.proyecto.ui.view.TAG_LOGS
import com.example.proyecto.ui.vistamodelo.mainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class repository {

 var factura: InvoiceResponseVO? = null
    suspend fun getAllFacturas(): InvoiceResponseVO? {
// esta seroia como nuestra pequeña base de datos nuestro buck -end
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                RetrofitHelper().getRetrofit().create(Apiservice::class.java).getAllFacturas()
                    .execute()
              factura = call.body()

            Log.i(TAG_LOGS, factura.toString()+"ss")

            // las facturas se las pasamos a nuestra Facturaprovider.
            FacturaProvider.facturas = factura!!.facturas
            //mainViewModel().facturamodel.postValue(factura)

        }
        return factura
    }

}
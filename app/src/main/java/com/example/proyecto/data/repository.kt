package com.example.proyecto.data

import android.util.Log
import com.example.proyecto.Network.Apiservice
import com.example.proyecto.Network.RetrofitHelper
import com.example.proyecto.data.dataBase.FacturaBD
import com.example.proyecto.data.dataBase.FacturasBasedeDatos
import com.example.proyecto.data.dataBase.dao.entidadDao
import com.example.proyecto.data.models.FacturaProvider
import com.example.proyecto.data.models.InvoiceResponseVO
import com.example.proyecto.data.models.InvoiceVO
import com.example.proyecto.ui.view.TAG_LOGS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class repository {

 var factura: InvoiceResponseVO? = null
     val facturaDao:entidadDao?= null


    suspend fun getAllFacturas(): InvoiceResponseVO? {
// esta seroia como nuestra pequeña base de datos nuestro buck -end
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                RetrofitHelper().getRetrofit().create(Apiservice::class.java).getAllFacturas()
                    .execute()
              factura = call.body()


            Log.i(TAG_LOGS, factura.toString()+"ss")

            // las facturas se las pasamos a nuestra Facturaprovider.
//            FacturaProvider.facturas = factura!!.facturas
            //mainViewModel().facturamodel.postValue(factura)

        }
        return factura
    }

 suspend fun getfacturasfromdatabase():List<InvoiceVO>{

    val response:List<InvoiceVO> =facturaDao!!.getAllentidad()
    return response
}

    suspend fun insertAllFacturas(facturas:List<InvoiceVO>){

        CoroutineScope(Dispatchers.IO).launch {
            val call =
                RetrofitHelper().getRetrofit().create(Apiservice::class.java).getAllFacturas()
                    .execute()
            factura = call.body()
            facturaDao!!.pushAllentidad(factura!!.facturas)
        }
    }

}
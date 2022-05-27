package com.example.proyecto.data

import android.util.Log
import androidx.room.Room
import com.example.proyecto.Network.Apiservice
import com.example.proyecto.Network.RetrofitHelper
import com.example.proyecto.data.dataBase.FacturaBD
import com.example.proyecto.data.dataBase.FacturasBasedeDatos
import com.example.proyecto.data.dataBase.dao.entidadDao
import com.example.proyecto.data.models.FacturaProvider
import com.example.proyecto.data.models.InvoiceResponseVO
import com.example.proyecto.data.models.InvoiceVO
import com.example.proyecto.ui.view.TAG_LOGS
import com.example.proyecto.ui.view.context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class repository {
     var factura:  List<InvoiceVO>?=null

    var facturas4: List<InvoiceVO>? = null
     val facturaDao:entidadDao?= null


    suspend fun getAllFacturas(): List<InvoiceVO>? {
// esta seroia como nuestra pequeña base de datos nuestro buck -end
        CoroutineScope(Dispatchers.IO).launch {

            val call =
                RetrofitHelper().getRetrofit().create(Apiservice::class.java).getAllFacturas()
                    .execute()
            if (call.isSuccessful){
                factura = call.body()!!.facturas
                val bd = Room.databaseBuilder(context, FacturasBasedeDatos::class.java,"facturas").build()

                bd.getFacturaDao().pushAllentidad(call.body()!!.facturas)



            }else{

                val bd =
                    Room.databaseBuilder(context, FacturasBasedeDatos::class.java, "facturas")
                        .build()

                factura = bd.getFacturaDao().getAllentidad()
                Log.i(TAG_LOGS, factura.toString() + "57")


            }






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
            factura = call.body()!!.facturas
            facturaDao!!.pushAllentidad(factura!!)
        }
    }

}
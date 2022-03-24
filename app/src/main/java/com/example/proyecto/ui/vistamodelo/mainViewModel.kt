package com.example.proyecto.ui.vistamodelo

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.data.dataBase.dao.entidadDao
import com.example.proyecto.data.models.InvoiceResponseVO
import com.example.proyecto.data.models.InvoiceVO
import com.example.proyecto.domain.Casodeuso1
import com.example.proyecto.ui.view.FacturaHolder
import com.example.proyecto.ui.view.MainActivity
import com.example.proyecto.ui.view.TAG_LOGS
import kotlinx.coroutines.launch

class mainViewModel:ViewModel() {

    //encapsulamos nuestro modelo de datos en el liveData
    val facturamodel = MutableLiveData<InvoiceResponseVO?>()
    //este live data lo creamos para que solo se pueda leer no como el mutable en el que podemos eliminar y añadir mas datos.
    val facturaslivedata:LiveData<InvoiceResponseVO?> = facturamodel
     val repository= com.example.proyecto.data.repository()
    val casodeuso1= Casodeuso1()

    fun onCreate() {
        viewModelScope.launch {
            val result:InvoiceResponseVO? = casodeuso1()
            //hacemos condicion de si es o no
                if (result!= null) {
                    facturamodel.postValue(result)
                    //como no esta vacio hacemos el insert a la base de datos

                }
        }

    }


}
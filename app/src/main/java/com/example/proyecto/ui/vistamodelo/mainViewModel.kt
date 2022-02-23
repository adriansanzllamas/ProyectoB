package com.example.proyecto.ui.vistamodelo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.data.models.InvoiceVO
import com.example.proyecto.domain.Casodeuso1
import kotlinx.coroutines.launch

class mainViewModel:ViewModel() {


    //encapsulamos nuestro modelo de datos en el liveData
    val facturamodel=MutableLiveData<InvoiceVO>()
    var getFacturaUseCase= Casodeuso1()

    fun onCreate() {
        viewModelScope.launch {
            val result = getFacturaUseCase()
            //hacemos condicion de si es o no
        }
    }
}
package com.example.proyecto.ui.vistamodelo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyecto.data.models.InvoiceVO

class mainViewModel:ViewModel() {
    //encapsulamos nuestro modelo de datos en el liveData
    val facturamodel=MutableLiveData<InvoiceVO>()

}
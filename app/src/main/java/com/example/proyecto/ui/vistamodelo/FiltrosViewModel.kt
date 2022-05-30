package com.example.proyecto.ui.vistamodelo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyecto.data.models.InvoiceVO

class FiltrosViewModel : ViewModel() {

    //encapsulamos nuestro modelo de datos en el liveData
    val filtrosmodel = MutableLiveData<List<InvoiceVO>>()
}
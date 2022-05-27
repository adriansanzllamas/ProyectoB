package com.example.proyecto.domain

import com.example.proyecto.data.models.InvoiceResponseVO
import com.example.proyecto.data.models.InvoiceVO
import com.example.proyecto.data.repository
import com.example.proyecto.ui.view.FacturaHolder
import kotlin.coroutines.coroutineContext

class Casodeuso1 {
    private val repositorio= repository()

    suspend operator  fun invoke(): List<InvoiceVO>? = repositorio.getAllFacturas()



}
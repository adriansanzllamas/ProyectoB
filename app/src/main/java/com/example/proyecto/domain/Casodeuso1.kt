package com.example.proyecto.domain

import com.example.proyecto.data.models.InvoiceResponseVO
import com.example.proyecto.data.models.InvoiceVO
import com.example.proyecto.data.repository

class Casodeuso1 {
    private val repositorio= repository()

    suspend operator  fun invoke(): InvoiceResponseVO? = repositorio.getAllFacturas()
}
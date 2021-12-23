package com.example.proyecto.models

data class InvoiceResponseVO(
    private val numFacturas: Double,
    private val facturas: List<InvoiceVO>
) {

}



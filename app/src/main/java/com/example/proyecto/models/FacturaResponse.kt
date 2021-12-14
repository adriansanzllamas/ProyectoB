package com.example.proyecto.models

class FacturaResponse {
    private var numFacturas = 0.0
    private var facturas: List<Facturaindividual>? = null


    fun getFacturas(): List<Facturaindividual>? {
        return facturas
    }

    fun InvoiceResponseVO(numFacturas: Double, facturas: List<Facturaindividual>?) {
        this.numFacturas = numFacturas
        this.facturas = facturas
    }

    override fun toString(): String {
        return "InvoiceResponse(numFacturas=" + numFacturas + ", facturas=" + facturas + ")"
    }
}
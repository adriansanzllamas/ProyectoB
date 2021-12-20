package com.example.proyecto.models

 data class InvoiceResponseVO(private val numFacturas: Double,private  val facturas: List<InvoiceVO>) {
   // private val facturas: List<InvoiceVO>
    /*fun getFacturas(): List<InvoiceVO> {
        return facturas
    }

    override fun toString(): String {
        return "InvoiceResponse(numFacturas=" + numFacturas + ", facturas=" + facturas + ")"
    }

    init {
        this.facturas = factura
    }*/
}
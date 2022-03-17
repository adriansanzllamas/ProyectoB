package com.example.proyecto.data.models

import com.example.proyecto.data.dataBase.entities.entidad


data class InvoiceVO(
     val descEstado: String,
    val importeOrdenacion: Double,
     val fecha: String
)

fun entidad.toDomain()=InvoiceVO(descEstado,importeOrdenacion,fecha)

package com.example.proyecto.models

import java.util.*
import kotlin.collections.ArrayList

data class Facturas(
    private final var  numFacturas:Double,
    private final var facturas:List<facturaindividual>
)
 data class facturaindividual(
    private final var descEstado:String,
    private final var importeOrdenacion:Long,
    private final var fecha:Date
)



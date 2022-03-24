package com.example.proyecto.data.dataBase

import android.app.Application
import androidx.room.Room
// en esta clase lo que hacemos es montar nuestro Room con la base de datos.
class FacturaBD: Application() {
// desde cualquir lado podremos acceder a la base de datos con esta clase
    val room =Room.databaseBuilder(applicationContext,FacturasBasedeDatos::class.java,"facturas").build()
}
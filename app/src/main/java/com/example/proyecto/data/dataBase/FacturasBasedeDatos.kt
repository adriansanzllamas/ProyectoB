package com.example.proyecto.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyecto.data.dataBase.dao.entidadDao

import com.example.proyecto.data.models.InvoiceVO

//la version se utiliza para hacer migraciones.
@Database(entities = [InvoiceVO::class],version = 1)
 abstract class FacturasBasedeDatos: RoomDatabase() {

     abstract fun getFacturaDao():entidadDao
}
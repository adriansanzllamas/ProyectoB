package com.example.proyecto.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyecto.data.dataBase.dao.entidadDao
import com.example.proyecto.data.dataBase.entities.entidad
//la version se utiliza para hacer migraciones.
@Database(entities = [entidad::class],version = 1)
 abstract class FacturasBasedeDatos: RoomDatabase() {

     abstract fun getFacturaDao():entidadDao
}
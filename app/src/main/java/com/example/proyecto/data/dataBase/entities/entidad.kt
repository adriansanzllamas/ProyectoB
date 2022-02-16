package com.example.proyecto.data.dataBase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "tabla_entidad")//poner el nombre a la tabla
data class entidad(@ColumnInfo(name="descEstado") val descEstado: String,
                   @ColumnInfo(name="importeOrdenacion")val importeOrdenacion: Double,
                   @ColumnInfo(name="fecha")val fecha: String)

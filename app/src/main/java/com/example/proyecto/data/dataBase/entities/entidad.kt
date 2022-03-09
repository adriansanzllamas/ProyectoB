package com.example.proyecto.data.dataBase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_entidad")//poner el nombre a la tabla
data class entidad( @PrimaryKey(autoGenerate = true)
                    @ColumnInfo(name="id") val id:Int=0,
                    @ColumnInfo(name="descEstado") val descEstado: String,
                    @ColumnInfo(name="importeOrdenacion")val importeOrdenacion: Double,
                    @ColumnInfo(name="fecha")val fecha: String,
                  )

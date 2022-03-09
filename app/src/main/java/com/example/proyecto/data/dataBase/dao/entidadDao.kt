package com.example.proyecto.data.dataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyecto.data.dataBase.entities.entidad

//aqui tendremos nuestras queries
@Dao
interface entidadDao {
    @Query("SELECT * FROM tabla_entidad ORDER BY descEstado DESC")
    suspend fun getAllentidad():List<entidad>
//utilizamos el suspend para que estas funciones no se ejecuten en el hilo principal.
    //inserts
    //lo que hacemos aqui es poner una condicion para que reemplace lo que hay en la base de datos por la nueva informacion
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun pushAllentidad(facturas:List<entidad>)

}
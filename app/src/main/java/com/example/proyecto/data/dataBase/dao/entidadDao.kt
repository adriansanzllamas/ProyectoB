package com.example.proyecto.data.dataBase.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.proyecto.data.dataBase.entities.entidad

//aqui tendremos nuestras queries
@Dao
interface entidadDao {
    @Query("SELECT * FROM tabla_entidad ORDER BY descEstado DESC")
    suspend fun getAllentidad():List<entidad>

}
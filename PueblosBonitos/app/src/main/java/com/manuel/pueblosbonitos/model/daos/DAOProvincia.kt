package com.manuel.pueblosbonitos.model.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.manuel.pueblosbonitos.model.entities.ProvinciaConPueblos

@Dao
interface DAOProvincia {
    @Query("SELECT * FROM Provincia WHERE comunidad = :comunidadId")
    fun getAllProvincias(comunidadId: Int): LiveData<List<ProvinciaConPueblos>>
}
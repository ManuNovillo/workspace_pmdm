package com.manuel.pueblosbonitos.model.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.manuel.pueblosbonitos.model.entities.Pueblo
import com.manuel.pueblosbonitos.model.entities.PuebloConProvincia

@Dao
interface DAOPueblo {
    @Query("""SELECT * 
              FROM Pueblo pu
              WHERE pu.provincia IN (
                SELECT pr.id
                FROM Provincia pr
                WHERE pr.comunidad = :comunidadId
              )""")
    fun getAllPueblosByComunidad(comunidadId: Int): LiveData<List<PuebloConProvincia>>

    @Update
    fun updatePueblo(pueblo: Pueblo)
}
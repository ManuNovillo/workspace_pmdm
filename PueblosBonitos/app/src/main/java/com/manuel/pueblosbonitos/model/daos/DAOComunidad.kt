package com.manuel.pueblosbonitos.model.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.manuel.pueblosbonitos.model.entities.Comunidad

@Dao
interface DAOComunidad {
    @Query("SELECT * FROM comunidad")
    fun getAllComunidades(): LiveData<List<Comunidad>>
}
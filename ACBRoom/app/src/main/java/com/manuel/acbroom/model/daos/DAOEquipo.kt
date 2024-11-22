package com.manuel.acbroom.model.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.manuel.acbroom.model.EquipoConJugadores

@Dao
interface DAOEquipo {
    @Transaction
    @Query("SELECT * FROM Equipo e ORDER BY e.nombre")
    fun getAllEquipos(): LiveData<List<EquipoConJugadores>>
}
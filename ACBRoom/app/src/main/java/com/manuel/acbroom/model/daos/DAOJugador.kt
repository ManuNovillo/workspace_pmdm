package com.manuel.acbroom.model.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.manuel.acbroom.model.Jugador
import com.manuel.acbroom.model.JugadorConEquipo

@Dao
interface DAOJugador {

    @Transaction
    @Query
    ("SELECT * FROM Jugador j ORDER BY j.nombre")
    fun getAllJugadores(): LiveData<List<JugadorConEquipo>>

    @Update
    fun update(jugador: Jugador)
}
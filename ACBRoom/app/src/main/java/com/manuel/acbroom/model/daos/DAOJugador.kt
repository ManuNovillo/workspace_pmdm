package com.manuel.acbroom.model.daos

import androidx.room.Dao
import androidx.room.Update
import com.manuel.acbroom.model.Jugador

@Dao
interface DAOJugador {

    @Update
    fun update(jugador: Jugador)
}
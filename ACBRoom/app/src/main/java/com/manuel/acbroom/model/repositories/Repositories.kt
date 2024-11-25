package com.manuel.acbroom.model.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.manuel.acbroom.model.EquipoConJugadores
import com.manuel.acbroom.model.Jugador
import com.manuel.acbroom.model.database.AcbDB

class JugadorRepository(application: Application) {

    val daoJugador = AcbDB.getDatabase(application).daoJugador()

    fun update(jugador: Jugador) = daoJugador.update(jugador)
    fun getAllJugadores() = daoJugador.getAllJugadores()

}

class EquipoRepository(application: Application) {

    val daoEquipo = AcbDB.getDatabase(application).daoEquipo()

    fun getAllEquipos() = daoEquipo.getAllEquipos()
}
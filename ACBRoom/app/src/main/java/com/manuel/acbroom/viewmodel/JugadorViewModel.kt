package com.manuel.acbroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.manuel.acbroom.model.Jugador
import com.manuel.acbroom.model.repositories.JugadorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class JugadorViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = JugadorRepository(application)

    fun update(jugador: Jugador) {
        GlobalScope.launch {
            repository.update(jugador)
            launch(Dispatchers.Main) {}
        }
    }

    fun getAllJugadores() = repository.getAllJugadores()
}
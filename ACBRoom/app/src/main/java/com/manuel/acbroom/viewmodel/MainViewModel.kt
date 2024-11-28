package com.manuel.acbroom.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuel.acbroom.R
import com.manuel.acbroom.model.Equipo
import com.manuel.acbroom.model.Jugador
import com.manuel.acbroom.model.JugadorConEquipo
import com.manuel.acbroom.util.Util
import com.manuel.acbroom.view.MainActivity

class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    val jugadorConEquipo = mutableStateOf<JugadorConEquipo?>(null)
    val jugadoresConEquipo = mutableStateListOf<JugadorConEquipo>()
    val icono = mutableIntStateOf(R.drawable.player)
    val isPlayer = mutableStateOf(true)
    val equipo = mutableStateOf<Equipo?>(null)
    private var jugadorViewModel: JugadorViewModel

    init {
        Util.inyecta(mainActivity, "acb.sqlite")
        jugadorViewModel = ViewModelProvider(mainActivity)[JugadorViewModel::class]
        jugadorViewModel.getAllJugadores().observe(mainActivity) { jugadoresDatos ->
            jugadoresConEquipo.clear()
            jugadoresConEquipo.addAll(jugadoresDatos)
        }
    }

    fun changeIcono() {
        isPlayer.value = !isPlayer.value
        icono.intValue = if (isPlayer.value) R.drawable.player else R.drawable.team
    }

    fun prepararJugadorScreen(jugadorConEquipo: JugadorConEquipo) {
        this.jugadorConEquipo.value = jugadorConEquipo
    }

    fun updateLikes(jugador: Jugador) {
        jugador.likes++
        jugadorViewModel.update(jugador)
    }

}
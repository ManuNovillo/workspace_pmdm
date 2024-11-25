package com.manuel.acbroom.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuel.acbroom.model.JugadorConEquipo
import com.manuel.acbroom.util.Util
import com.manuel.acbroom.view.MainActivity

class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    val jugadoresConEquipo = mutableStateListOf<JugadorConEquipo>()

    init {
        Util.inyecta(mainActivity, "acb.sqlite")
        val jugadorViewModel = ViewModelProvider(mainActivity)[JugadorViewModel::class]
        jugadorViewModel.getAllJugadores().observe(mainActivity) { jugadoresDatos ->
            jugadoresConEquipo.clear()
            jugadoresConEquipo.addAll(jugadoresDatos)
        }

    }
}
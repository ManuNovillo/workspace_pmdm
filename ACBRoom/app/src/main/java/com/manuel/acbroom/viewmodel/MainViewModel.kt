package com.manuel.acbroom.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuel.acbroom.model.EquipoConJugadores
import com.manuel.acbroom.util.Util
import com.manuel.acbroom.view.MainActivity

class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    val equipos = mutableStateListOf<EquipoConJugadores>()

    init {
        Util.inyecta(mainActivity, "acb.sqlite")
        val equipoViewModel = ViewModelProvider(mainActivity)[EquipoViewModel::class]
        equipoViewModel.getAllEquipos().observe(mainActivity) {
            equipos.addAll(it)
        }

    }
}
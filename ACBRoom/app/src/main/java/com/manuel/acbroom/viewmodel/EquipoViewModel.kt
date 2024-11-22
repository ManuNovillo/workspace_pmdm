package com.manuel.acbroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.manuel.acbroom.model.EquipoConJugadores
import com.manuel.acbroom.model.repositories.EquipoRepository

class EquipoViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = EquipoRepository(application)

    fun getAllEquipos() =  repository.getAllEquipos()
}
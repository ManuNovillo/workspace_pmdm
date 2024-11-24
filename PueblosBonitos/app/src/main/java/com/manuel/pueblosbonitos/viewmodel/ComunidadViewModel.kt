package com.manuel.pueblosbonitos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.manuel.pueblosbonitos.model.repositories.ComunidadRepository

class ComunidadViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ComunidadRepository(application)
    fun getAllComunidades() = repository.getAllComunidades()
}
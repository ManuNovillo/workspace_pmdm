package com.manuel.pueblosbonitos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.manuel.pueblosbonitos.model.repositories.ProvinciaRepository

class ProvinciaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProvinciaRepository(application)

    fun getAllProvincias(comunidadId: Int) = repository.getAllProvincias(comunidadId)

}
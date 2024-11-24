package com.manuel.pueblosbonitos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.manuel.pueblosbonitos.model.entities.Pueblo
import com.manuel.pueblosbonitos.model.repositories.PuebloRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PuebloViewModel(application: Application) : AndroidViewModel(application)  {

    val repository = PuebloRepository(application)

    fun getAllPueblosByComunidad(comunidadId: Int) = repository.getAllPueblosByComunidad(comunidadId)

    fun updatePueblo(pueblo: Pueblo) {
        GlobalScope.launch {
            repository.updatePueblo(pueblo)
            launch(Dispatchers.Main) {}
        }
    }
}
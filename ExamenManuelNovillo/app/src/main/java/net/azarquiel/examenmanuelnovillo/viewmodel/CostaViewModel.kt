package net.azarquiel.examenmanuelnovillo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.azarquiel.examenmanuelnovillo.model.CostaRepository

class CostaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CostaRepository(application)
    fun getAllCostas() = repository.getAllCostas()

}
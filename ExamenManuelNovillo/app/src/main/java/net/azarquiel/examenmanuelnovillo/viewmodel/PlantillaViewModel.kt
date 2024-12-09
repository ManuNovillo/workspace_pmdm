package net.azarquiel.examenmanuelnovillo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlantillaViewModel(application: Application) : AndroidViewModel(application) {
    //    private val repository = ComunidadRepository(application)
//    fun getAllComunidades() = repository.getAllComunidades()
//
//    fun updatePueblo(pueblo: Pueblo) {
//        GlobalScope.launch {
//            repository.updatePueblo(pueblo)
//            launch(Dispatchers.Main) {}
//        }
//    }
}
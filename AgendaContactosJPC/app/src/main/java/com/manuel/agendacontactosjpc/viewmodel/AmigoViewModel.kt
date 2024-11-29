package com.manuel.agendacontactosjpc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.manuel.agendacontactosjpc.model.entities.Amigo
import com.manuel.agendacontactosjpc.model.repositories.AmigoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AmigoViewModel(application: Application) : AndroidViewModel(application) {
    private val amigoRepository = AmigoRepository(application)

    fun getAllAmigos() = amigoRepository.getAllAmigos()

    fun insertarAmigo(amigo: Amigo) {
        GlobalScope.launch {
            amigoRepository.insertarAmigo(amigo)
            launch(Dispatchers.Main) {}
        }
    }
}
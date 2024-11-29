package com.manuel.agendacontactosjpc.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuel.agendacontactosjpc.model.entities.Amigo
import com.manuel.agendacontactosjpc.view.MainActivity

class MainViewModel(mainActivity: MainActivity) : ViewModel()  {

    val amigos = mutableListOf<Amigo>()
    val openDialog = mutableStateOf(false)

    init {
        val amigoVewModel = ViewModelProvider(mainActivity)[AmigoViewModel::class]
        amigoVewModel.getAllAmigos().observe(mainActivity) {amigosData ->
            amigos.clear()
            amigos.addAll(amigosData)
        }
    }

    fun setDialog(value: Boolean) {
        openDialog.value = value
    }

}
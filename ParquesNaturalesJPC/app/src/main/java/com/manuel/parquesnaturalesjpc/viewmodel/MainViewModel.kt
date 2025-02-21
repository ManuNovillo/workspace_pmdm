package com.manuel.parquesnaturalesjpc.viewmodel

import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.manuel.parquesnaturalesjpc.model.Comunidad
import com.manuel.parquesnaturalesjpc.model.Imagen
import com.manuel.parquesnaturalesjpc.model.Parque
import com.manuel.parquesnaturalesjpc.view.MainActivity

class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    val mainActivity by lazy { mainActivity }

    companion object {
        val MY_REPOSITORY_KEY = object : CreationExtras.Key<MainActivity> {}
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer {
                val activity = this[MY_REPOSITORY_KEY] as MainActivity
                MainViewModel(activity)
            }
        }
    }

    val comunidades = mutableStateListOf<Comunidad>()
    val parques = mutableStateListOf<Parque>()
    val imagenesParque = mutableStateListOf<Imagen>()
    lateinit var comunidadSeleccionada: Comunidad
    lateinit var parqueSeleccionado: Parque
    val dataviewmodel = ViewModelProvider(mainActivity)[DataViewModel::class.java]

    init {
        dataviewmodel.getComunidades()
            .observe(mainActivity) { comunidadesDatos ->
                comunidades.addAll(comunidadesDatos)
            } }

    fun loadParquesComunidad(comunidad: Comunidad) {
        comunidadSeleccionada = comunidad
        if (comunidadSeleccionada.id != 0) {
            dataviewmodel.getParquesByComunidad(comunidadSeleccionada.id)
                .observe(mainActivity) { parquesDatos ->
                    parques.clear()
                    parques.addAll(parquesDatos)
                }
        } else {
            dataviewmodel.getParques()
                .observe(mainActivity) { parquesDatos ->
                    parques.clear()
                    parques.addAll(parquesDatos)
                }
        }
    }

    fun loadParque(parque: Parque) {
        parqueSeleccionado = parque
        dataviewmodel.getImagenesByParque(parque.id)
            .observe(mainActivity) { imagenesDatos ->
                imagenesParque.clear()
                imagenesParque.addAll(imagenesDatos)
            }
    }

    fun darLike() {
        parqueSeleccionado.likes++
        dataviewmodel.darLike(parqueSeleccionado.id).observe(mainActivity) {
            it?.let {
                Toast.makeText(mainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
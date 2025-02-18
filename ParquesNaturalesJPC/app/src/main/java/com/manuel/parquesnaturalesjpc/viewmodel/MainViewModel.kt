package com.manuel.parquesnaturalesjpc.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuel.parquesnaturalesjpc.model.Comunidad
import com.manuel.parquesnaturalesjpc.model.Imagen
import com.manuel.parquesnaturalesjpc.model.Parque
import com.manuel.parquesnaturalesjpc.view.MainActivity

class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    val mainActivity by lazy { mainActivity }
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
            }
    }

    fun loadParquesComunidad(comunidad: Comunidad) {
        comunidadSeleccionada = comunidad
        dataviewmodel.getParquesByComunidad(comunidadSeleccionada.id)
            .observe(mainActivity) { parquesDatos ->
                parques.clear()
                parques.addAll(parquesDatos)
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
}
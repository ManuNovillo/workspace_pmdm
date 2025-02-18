package com.manuel.parquesnaturalesjpc.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manuel.parquesnaturalesjpc.api.MainRepository
import com.manuel.parquesnaturalesjpc.model.Comunidad
import com.manuel.parquesnaturalesjpc.model.Imagen
import com.manuel.parquesnaturalesjpc.model.Parque
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
// ……

/**
 * Created by Paco Pulido.
 */
class DataViewModel : ViewModel() {

    private var repository: MainRepository = MainRepository()

    fun getComunidades(): MutableLiveData<List<Comunidad>> {
        val comunidades = MutableLiveData<List<Comunidad>>()
        GlobalScope.launch(Main) {
            comunidades.value = repository.getComunidades()
        }
        return comunidades
    }

    fun getParques(): MutableLiveData<List<Parque>> {
        val parques = MutableLiveData<List<Parque>>()
        GlobalScope.launch(Main) {
            parques.value = repository.getParques()
        }
        return parques
    }

    fun getParquesByComunidad(idCiudad: Int): MutableLiveData<List<Parque>> {
        val parques = MutableLiveData<List<Parque>>()
        GlobalScope.launch(Main) {
            parques.value = repository.getParquesByComunidad(idCiudad)
        }
        return parques
    }

    fun getImagenesByParque(idParque: Int): MutableLiveData<List<Imagen>> {
        val imagenes = MutableLiveData<List<Imagen>>()
        GlobalScope.launch(Main) {
            imagenes.value = repository.getImagenesByParque(idParque)
        }
        return imagenes
    }

    fun darLike(idParque: Int): MutableLiveData<String> {
        val msg = MutableLiveData<String>()
        GlobalScope.launch(Main) {
            msg.value = repository.darLike(idParque)
        }
        return msg
    }
}

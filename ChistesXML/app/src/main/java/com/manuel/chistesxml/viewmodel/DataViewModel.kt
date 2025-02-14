package com.manuel.chistesxml.viewmodel

import MainRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manuel.chistesxml.model.Categoria
import com.manuel.chistesxml.model.Chiste
import com.manuel.chistesxml.model.Punto
import com.manuel.chistesxml.model.Usuario
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {

   private var repository: MainRepository = MainRepository()

   fun getCategorias(): MutableLiveData<List<Categoria>> {
       val categorias = MutableLiveData<List<Categoria>>()
       GlobalScope.launch(Main) { //envuelto en corrutina, los metodos dentro de ese hilo dentro de await y suspend
           categorias.value = repository.getCategorias() //dentro de livedata .value para acceder al dato
       }
       return categorias
   }

   fun saveChiste(chiste: Chiste):MutableLiveData<Chiste> {
       val chisteResponse = MutableLiveData<Chiste>()
       GlobalScope.launch(Main) {
           chisteResponse.value = repository.saveChiste(chiste)
       }
       return chisteResponse
   }

    fun getDataUsuarioPorNickPass(nick:String,pass:String): MutableLiveData<Usuario> {
        val usuario = MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuario.value = repository.getDataUsuarioPorNickPass(nick,pass)
        }
        return usuario
    }

    fun saveUsuario(usuario: Usuario):MutableLiveData<Usuario> {
        val usuarioResponse= MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuarioResponse.value = repository.saveUsuario(usuario)
        }
        return usuarioResponse
    }


    fun savePuntos(idchiste:String, punto:Punto):MutableLiveData<Punto> {
        val puntoResponse= MutableLiveData<Punto>()
        GlobalScope.launch(Main) {
            puntoResponse.value = repository.savePuntos(idchiste,punto)
        }
        return puntoResponse
    }

   fun getChistesByCategoria(idCategoria: String):MutableLiveData<List<Chiste>> {
       val chistes = MutableLiveData<List<Chiste>>()
       GlobalScope.launch(Main) {
           chistes.value = repository.getChistesByCategoria(idCategoria)
       }
       return chistes
   }

    fun getAvgChiste(idChiste: String):MutableLiveData<String> {
        val avg = MutableLiveData<String>()
        GlobalScope.launch(Main) {
            avg.value = repository.getAvgPuntos(idChiste)
        }
        return avg
    }
}








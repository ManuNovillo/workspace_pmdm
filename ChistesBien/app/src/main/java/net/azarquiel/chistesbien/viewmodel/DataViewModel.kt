package net.azarquiel.chistesbien.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.chistesbien.api.MainRepository
import net.azarquiel.chistesbien.model.Categoria
import net.azarquiel.chistesbien.model.Chiste
import net.azarquiel.chistesbien.model.Punto
import net.azarquiel.chistesbien.model.Usuario

class DataViewModel : ViewModel() {

   private var repository: MainRepository = MainRepository()

   @OptIn(DelicateCoroutinesApi::class)
   fun getCategorias(): MutableLiveData<List<Categoria>> {
       val categorias = MutableLiveData<List<Categoria>>()
       GlobalScope.launch(Main) {
           categorias.value = repository.getCategorias()
       }
       return categorias
   }

    @OptIn(DelicateCoroutinesApi::class)
    fun getChistesByCategoria(idCategoria: String): MutableLiveData<List<Chiste>> {
        val chistes = MutableLiveData<List<Chiste>>()
        GlobalScope.launch(Main) {
            chistes.value = repository.getChistesByCategoria(idCategoria)
        }
        return chistes
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getUsuario(nick:String, pass:String): MutableLiveData<Usuario> {
        val chistes = MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            chistes.value = repository.getUsuario(nick, pass)
        }
        return chistes
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getAvgPuntos(idChiste: String): MutableLiveData<Int> {
        val chistes = MutableLiveData<Int>()
        GlobalScope.launch(Main) {
            chistes.value = repository.getAvgPuntos(idChiste)
        }
        return chistes
    }

   fun saveUsuario(usuario: Usuario):MutableLiveData<Usuario> {
       val chistes = MutableLiveData<Usuario>()
       GlobalScope.launch(Main) {
           chistes.value = repository.saveUsuario(usuario)
       }
       return chistes
   }

    fun saveChiste(chiste: Chiste):MutableLiveData<Chiste> {
        val chistes = MutableLiveData<Chiste>()
        GlobalScope.launch(Main) {
            chistes.value = repository.saveChiste(chiste)
        }
        return chistes
    }

    fun puntuarChiste(idChiste:String, punto: Punto):MutableLiveData<Punto> {
        val chistes = MutableLiveData<Punto>()
        GlobalScope.launch(Main) {
            chistes.value = repository.puntuarChiste(idChiste, punto)
        }
        return chistes
    }
}

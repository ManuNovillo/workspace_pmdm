package net.azarquiel.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.retrofit.api.MainRepository
import net.azarquiel.retrofit.model.Comentario
import net.azarquiel.retrofit.model.Gafa
import net.azarquiel.retrofit.model.Marca
import net.azarquiel.retrofit.model.Usuario

class DataViewModel : ViewModel() {

    private var repository: MainRepository = MainRepository()

    fun getMarcas(): MutableLiveData<List<Marca>> {
        val marcas = MutableLiveData<List<Marca>>()
        GlobalScope.launch(Main) {
            marcas.value = repository.getMarcas()
        }
        return marcas
    }

    fun getGafasByMarca(idmarca: String): MutableLiveData<List<Gafa>> {
        val gafas = MutableLiveData<List<Gafa>>()
        GlobalScope.launch(Main) {
            gafas.value = repository.getGafasByMarca(idmarca)
        }
        return gafas
    }

    fun getComentariosByGafa(idGafa: String): MutableLiveData<List<Comentario>> {
        val comentarios = MutableLiveData<List<Comentario>>()
        GlobalScope.launch(Main) {
            comentarios.value = repository.getComentariosByGafa(idGafa)
        }
        return comentarios
    }

    fun getDataUsuarioPorNickPass(nick: String, pass: String): MutableLiveData<Usuario> {
        val usuario = MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuario.value = repository.getDataUsuarioPorNickPass(nick, pass)
        }
        return usuario
    }

    fun saveUsuario(usuario: Usuario): MutableLiveData<Usuario> {
        val usuarioData = MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuarioData.value = repository.saveUsuario(usuario)
        }
        return usuarioData
    }

    fun saveComentario(idgafa: String, comentario: Comentario): MutableLiveData<Comentario> {
        val comentarioData = MutableLiveData<Comentario>()
        GlobalScope.launch(Main) {
            comentarioData.value = repository.saveComentario(idgafa, comentario)
        }
        return comentarioData
    }
}

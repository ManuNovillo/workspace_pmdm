package net.azarquiel.retrofit.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.retrofit.model.Comentario
import net.azarquiel.retrofit.model.Gafa
import net.azarquiel.retrofit.model.Marca
import net.azarquiel.retrofit.model.Usuario
import net.azarquiel.retrofit.view.MainActivity

class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    private val sh = mainActivity.getSharedPreferences("usuario", Context.MODE_PRIVATE)

    private val dataViewModel: DataViewModel =
        ViewModelProvider(mainActivity)[DataViewModel::class.java]

    private val _marcas = MutableLiveData<List<Marca>>()
    val marcas: LiveData<List<Marca>> = _marcas

    private val _gafas = MutableLiveData<List<Gafa>>()
    val gafas: LiveData<List<Gafa>> = _gafas

    private val _usuario = MutableLiveData<Usuario>()
    val usuario: LiveData<Usuario> = _usuario

    private val _comentarios = MutableLiveData<List<Comentario>>()
    val comentarios: LiveData<List<Comentario>> = _comentarios

    private val _openLoginDialog = MutableLiveData<Boolean>()
    val openLoginDialog: LiveData<Boolean> = _openLoginDialog

    private val _openComentarioDialog = MutableLiveData<Boolean>()
    val openComentarioDialog: LiveData<Boolean> = _openComentarioDialog

    val mainActivity by lazy { mainActivity }

    init {
        val nick = sh.getString("nick", null)
        val pass = sh.getString("pass", null)
        if (nick != null && pass != null) {
            login(nick, pass)
        }
        dataViewModel.getMarcas().observe(mainActivity) { marcasData ->
            _marcas.value = marcasData
            marcasData.forEach {
                Log.d("Manu", it.nombre)
            }
        }
    }

    fun setLoginDialog(value: Boolean) {
        _openLoginDialog.value = value
    }

    fun login(nick: String, pass: String) {
        dataViewModel.getDataUsuarioPorNickPass(nick, pass).observe(mainActivity) { usuarioExistente ->
            if (usuarioExistente == null) {
                dataViewModel.saveUsuario(Usuario("1", nick, pass)).observe(mainActivity) { usuarioCreado ->
                    _usuario.value = usuarioCreado

                }
                Toast.makeText(mainActivity, "Usuario creado", Toast.LENGTH_SHORT).show()
            } else {
                _usuario.value = usuarioExistente
                Toast.makeText(mainActivity, "Login correcto", Toast.LENGTH_SHORT).show()
            }

            val editor = sh.edit()
            editor.putString("nick", nick)
            editor.putString("pass", pass)
            editor.apply()
        }
    }

    fun loadGafas(marca: Marca) {
        dataViewModel.getGafasByMarca(marca.id).observe(mainActivity) { gafasData ->
            _gafas.value = gafasData
        }
    }

    fun loadComentariosGafa(gafa: Gafa) {
        dataViewModel.getComentariosByGafa(gafa.id).observe(mainActivity) { comentariosData ->
            _comentarios.value = comentariosData
        }
    }

    fun setComentarioDialog(value: Boolean) {
        _openComentarioDialog.value = value
    }

    fun saveComentario(gafa: Gafa, comentario: Comentario) {
        dataViewModel.saveComentario(gafa.id, comentario).observe(mainActivity) { comentarioData ->
            loadComentariosGafa(gafa)
        }
    }
}
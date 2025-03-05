package net.azarquiel.chistesbien.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.chistesbien.model.Categoria
import net.azarquiel.chistesbien.model.Chiste
import net.azarquiel.chistesbien.model.Usuario
import net.azarquiel.chistesbien.view.MainActivity

class MainViewModel(val mainActivity: MainActivity) : ViewModel() {

    private val _categorias = MutableLiveData<List<Categoria>>()
    val categorias: LiveData<List<Categoria>> = _categorias

    private val _chistes = MutableLiveData<List<Chiste>>()
    val chistes: LiveData<List<Chiste>> = _chistes

    private val _usuario = MutableLiveData<Usuario>()
    val usuario: LiveData<Usuario> = _usuario

    private val _avg = MutableLiveData<Int>()
    val avg: LiveData<Int> = _avg

    private val _openDialog = MutableLiveData<Boolean>()
    val openDialog: LiveData<Boolean> = _openDialog

    val dataViewModel: DataViewModel

    init {
        dataViewModel = ViewModelProvider(mainActivity)[DataViewModel::class.java]
        dataViewModel.getCategorias().observe(mainActivity) {
            _categorias.value = it
        }
    }

    fun getChistes(idCategoria: String) {
        dataViewModel.getChistesByCategoria(idCategoria).observe(mainActivity) {
            _chistes.value = it
        }

    }

    fun setDialog(openDialog: Boolean) {
        _openDialog.value = openDialog
    }

    fun saveChiste(chiste: Chiste) {
        dataViewModel.saveChiste(chiste)
    }
}
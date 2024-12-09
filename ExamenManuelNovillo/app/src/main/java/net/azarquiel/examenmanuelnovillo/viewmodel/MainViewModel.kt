package net.azarquiel.examenmanuelnovillo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.azarquiel.examenmanuelnovillo.util.Util
import net.azarquiel.examenmanuelnovillo.view.MainActivity

class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    private var _datos = MutableLiveData<Int>()
    val datos: LiveData<Int> = _datos

    val mainActivity = mainActivity

    init {
        Util.inyecta(mainActivity, "pueblosbonitos.sqlite")
//        val comunidadViewModel = ViewModelProvider(mainActivity)[ComunidadViewModel::class.java]
//        comunidadViewModel.getAllComunidades().observe(mainActivity) { comunidadesDatos ->
//            comunidades.addAll(comunidadesDatos)
    }
}
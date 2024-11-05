package com.manuel.famososdbjpc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.manuel.famososdbjpc.model.Famoso
import com.manuel.famososdbjpc.util.Util
import com.manuel.famososdbjpc.view.MainActivity

class MainViewModel(mainActivity: MainActivity) {

    private var _famosos = MutableLiveData<List<Famoso>>()
    val famosos: LiveData<List<Famoso>> = _famosos
    init {
        Util.inyecta(mainActivity, "famososDB.db")
        val famosoViewModel = ViewModelProvider(mainActivity).get(FamosoViewModel::class.java)
        famosoViewModel.getFamosos().observe(mainActivity) { famosos ->
            _famosos.value = famosos
        }
    }
}

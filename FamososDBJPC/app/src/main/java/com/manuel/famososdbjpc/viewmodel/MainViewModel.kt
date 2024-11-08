package com.manuel.famososdbjpc.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.manuel.famososdbjpc.model.Famoso
import com.manuel.famososdbjpc.util.Util
import com.manuel.famososdbjpc.view.MainActivity

class MainViewModel(val mainActivity: MainActivity) {

    private var _famosos = MutableLiveData<List<Famoso>>()
    val famosos: LiveData<List<Famoso>> = _famosos
    init {
        Util.inyecta(mainActivity, "famosos.db")
        val famosoViewModel = ViewModelProvider(mainActivity).get(FamosoViewModel::class.java)
        famosoViewModel.getFamosos().observe(mainActivity) { famosos ->
            famosos.forEach {
                Log.d("MANUEL", it.toString())
            }
            _famosos.value = famosos
        }
    }
}

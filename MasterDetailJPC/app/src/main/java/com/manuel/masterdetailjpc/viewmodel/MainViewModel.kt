package com.manuel.masterdetailjpc.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.manuel.masterdetailjpc.MainActivity

class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    val name = mutableStateOf("")

    fun changeName(newName: String) {
        name.value = newName
    }

}
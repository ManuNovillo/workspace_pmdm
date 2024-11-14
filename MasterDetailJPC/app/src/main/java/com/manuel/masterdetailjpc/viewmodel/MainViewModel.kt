package com.manuel.masterdetailjpc.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val name = mutableStateOf("")

    fun changeName(newName: String) {
        name.value = newName
    }

}
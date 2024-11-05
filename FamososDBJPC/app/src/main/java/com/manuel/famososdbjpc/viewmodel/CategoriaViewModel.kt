package com.manuel.famososdbjpc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.manuel.famososdbjpc.model.CategoriaRepository

class CategoriaViewModel(application: Application) : AndroidViewModel(application) {
    private var categoriaRepository = CategoriaRepository(application)
    fun getCategoria(categoriaId: Int) = categoriaRepository.getCategoria(categoriaId)
}
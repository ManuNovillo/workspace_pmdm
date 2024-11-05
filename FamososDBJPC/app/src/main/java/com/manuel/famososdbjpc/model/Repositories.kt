package com.manuel.famososdbjpc.model

import android.app.Application

class FamosoRepository(application: Application) {
    val famosoDao = FamososDB.getDatabase(application).famosoDao()
    fun getFamosos() = famosoDao.getFamosos()
}

class CategoriaRepository(application: Application) {
    val categoriaDao = FamososDB.getDatabase(application).categoriaDao()
    fun getCategoria(categoriaId: Int) = categoriaDao.getCategoria(categoriaId)
}
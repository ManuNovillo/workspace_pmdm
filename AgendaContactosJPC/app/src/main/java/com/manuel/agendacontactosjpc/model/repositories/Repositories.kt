package com.manuel.agendacontactosjpc.model.repositories

import android.app.Application
import com.manuel.agendacontactosjpc.model.db.AgendaDB
import com.manuel.agendacontactosjpc.model.entities.Amigo

class AmigoRepository(application: Application) {
    private val amigoDao = AgendaDB.getDatabase(application).amigoDao()

    fun getAllAmigos() = amigoDao.getAllAmigos()

    fun insertarAmigo(amigo: Amigo) = amigoDao.insertarAmigo(amigo)
}
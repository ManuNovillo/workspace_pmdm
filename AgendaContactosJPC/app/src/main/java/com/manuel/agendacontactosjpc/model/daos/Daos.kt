package com.manuel.agendacontactosjpc.model.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.manuel.agendacontactosjpc.model.entities.Amigo

@Dao
interface AmigoDao {
    @Query("SELECT * FROM Amigo")
    fun getAllAmigos(): LiveData<List<Amigo>>

    @Insert
    fun insertarAmigo(amigo: Amigo)
}
package com.manuel.famososdbjpc.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.manuel.famososdbjpc.model.Categoria
import com.manuel.famososdbjpc.model.Famoso

@Dao
interface FamosoDao {
    @Query("SELECT * FROM Famoso")
    fun getFamosos(): LiveData<List<Famoso>>
}

@Dao
interface CategoriaDao {
    @Query("SELECT * FROM Categoria where c_id = :categoriaId")
    fun getCategoria(categoriaId: Int): LiveData<Categoria>
}
package com.manuel.famososdbjpc.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query
import com.manuel.famososdbjpc.model.Categoria
import com.manuel.famososdbjpc.model.Famoso

@Dao
interface FamosoDao {
    @Query("SELECT * FROM Famoso")
    fun getFamosos(): MutableLiveData<List<Famoso>>
}

@Dao
interface CategoriaDao {
    @Query("SELECT * FROM Categoria where c_id = :categoriaId")
    fun getCategoria(categoriaId: Int): MutableLiveData<Categoria>
}
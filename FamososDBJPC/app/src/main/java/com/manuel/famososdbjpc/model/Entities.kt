package com.manuel.famososdbjpc.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Famoso", foreignKeys = [
    ForeignKey(entity = Categoria::class, parentColumns = ["c_id"], childColumns = ["c_id"])
])
data class Famoso(
    @PrimaryKey
    @ColumnInfo(name = "p_id")
    var id: Int,
    @ColumnInfo(name = "p_name_es")
    var nombre: String,
    @ColumnInfo(name = "c_id")
    var categoriaId: Int
)

@Entity(tableName = "Categoria")
data class Categoria(
    @PrimaryKey
    @ColumnInfo(name = "c_id")
    var id: Int,
    @ColumnInfo(name = "c_name_es")
    var nombre: String
)
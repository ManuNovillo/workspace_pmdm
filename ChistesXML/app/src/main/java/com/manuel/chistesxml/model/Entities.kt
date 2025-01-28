package com.manuel.chistesxml.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Chiste(
    val id: String,
    @SerializedName("idcategoria")
    val idCategoria: String,
    val contenido: String,
): Serializable

data class Categoria(
    val id: String,
    val nombre: String,
): Serializable

data class Usuario (
    @SerializedName("idusuario")
    var id: Int,
    var nick: String,
    var pass: String
): Serializable

data class Puntos (
    var id: Int,
    var idusuario: Int,
    var idbar: Int,
    var puntos: Int
): Serializable

data class Punto(
    val id: String,
    @SerializedName("idchiste")
    val idChiste: String,
)

data class Result(
    val categorias: List<Categoria>,
    val chistes: List<Chiste>,
    val usuario: Usuario,
    val avg: Int,
    val chiste: Chiste,
    val puntos: Puntos
): Serializable

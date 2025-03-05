package net.azarquiel.chistesbien.model

import java.io.Serializable

data class Categoria(
    val id: String,
    val nombre: String
): Serializable

data class Chiste(
    val contenido: String,
    val id: String,
    val idcategoria: String
): Serializable

data class Usuario(
    val id: String,
    val nick: String,
    val pass: String
): Serializable

data class Punto(
    val id: String,
    val idchiste: String,
    val puntos: String
): Serializable

data class Result(
    val categorias: List<Categoria>,
    val chistes: List<Chiste>,
    val usuario: Usuario,
    val avg: Int,
    val punto: Punto,
    val chiste: Chiste
)
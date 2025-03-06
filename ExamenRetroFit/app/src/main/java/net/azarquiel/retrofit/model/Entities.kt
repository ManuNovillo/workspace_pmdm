package net.azarquiel.retrofit.model

import java.io.Serializable

data class Marca(
    val id: String,
    val imagen: String,
    val nombre: String
): Serializable

data class Gafa(
    val id: String,
    val imagen: String,
    val marca: String,
    val nombre: String,
    val precio: String
): Serializable

data class Comentario(
    val comentario: String,
    val fecha: String,
    val gafa: String,
    val id: String,
    val usuario: String = "",
    val nick: String = ""
): Serializable

data class Usuario(
    val id: String,
    val nick: String,
    val pass: String
): Serializable

data class Result(
    val marcas: List<Marca>,
    val gafas: List<Gafa>,
    val comentarios: List<Comentario>,
    val comentario: Comentario,
    val usuario: Usuario,
)
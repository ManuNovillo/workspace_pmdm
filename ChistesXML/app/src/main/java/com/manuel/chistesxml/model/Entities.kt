package com.manuel.chistesxml.model

import java.io.Serializable

data class Usuario (
    var id: String,
    var nick: String,
    var pass: String
): Serializable // para poder pasarlo entre paginas (mete saca)


data class Categoria ( //data class solo tienen datos no tienen método más alla de setter y getter
    var id: String,
    var nombre: String
): Serializable


data class Punto (
    var id: String,
    var idchiste: String,
    var puntos: String
): Serializable

data class Chiste ( //data class solo tienen datos no tienen método más alla de setter y getter
    var id: String,
    var idcategoria: String,
    var contenido: String
): Serializable


data class Result( //clase grande que tiene una lista de categorias y chistes  (se declara todos los json grandes de la api)
    val categorias: List<Categoria>,
    val chistes: List<Chiste>,
    val usuario: Usuario,
    val chiste: Chiste,
    val punto: Punto,
    val avg:String
)

package com.manuel.parquesnaturalesjpc.model

data class Comunidad(
    val id: Int,
    val nombre: String,
)

data class Parque(
    val id: Int,
    val nombre: String,
    val imagen: String,
    val mapa: String,
    val fondo: String,
    val comunidad: Int,
    val descripcion: String,
    var likes: Int
)

data class Imagen(
    val id: Int,
    val parque: Int,
    val foto: String
)

data class Result(
    val comunidades: List<Comunidad>,
    val parques: List<Parque>,
    val imagenes: List<Imagen>,
    val msg: String
)




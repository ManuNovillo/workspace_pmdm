package com.manuel.avesbien.model

data class Zona(
    val formaciones_principales: String,
    val geom_lat: String,
    val geom_lon: String,
    val id: String,
    val localizacion: String,
    val nombre: String,
    val presentacion: String
)

data class Comentario(
    val comentario: String,
    val fecha: String,
    val id: String,
    val nick: String,
    val recurso: String
)

data class Recurso(
    val id: String,
    val titulo: String,
    val url: String,
    val zona: Zona,
)

data class Result(
    val zonas: List<Zona>,
    val recursos: List<Recurso>,
    val comentarios: List<Comentario>
)
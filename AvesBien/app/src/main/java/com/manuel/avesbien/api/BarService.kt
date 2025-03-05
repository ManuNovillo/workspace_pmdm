package com.manuel.avesbien.api

import com.manuel.avesbien.model.Comentario
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*
import com.manuel.avesbien.model.Result

/**
* Created by Paco Pulido.
*/
interface AvesService {
// No necesita nada para trabajar
@GET("zonas")
fun getZonas(): Deferred<Response<Result>>

// variable idbar en la ruta de la url => @Path
@GET("zona/{idzona}/recursos")
fun getDataBar(@Path("idzona") idzona: Int): Deferred<Response<Result>>
// nick y pass variables sueltas en la url?nick=paco&pass=paco => @Query
@GET("recurso/{idrecurso}/comentarios")
fun getDataUsuarioPorNickPass(
       @Query("nick") nick: String,
       @Query("pass") pass: String): Deferred<Response<Result>>
// post con objeto => @Body
@POST("comentario")
fun saveComentario(@Body comentario: Comentario): Deferred<Response<Result>>

// post con variables sueltas => @Field y Obligatorio @FormUrlEncoded
@FormUrlEncoded
@POST("bar/{idbar}/puntos")
fun savePuntos(@Path("idbar") idbar: Int,
              @Field("idusuario") idusuario: Int,
              @Field("puntos") puntos: Int): Deferred<Response<Result>>

// ……..   resto de métodos de la interfaz ………..
}
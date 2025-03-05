package net.azarquiel.retrofit.api

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*
/**
* Created by Paco Pulido.
*/
interface BarService {
// No necesita nada para trabajar
@GET("bar")
fun getDataBares(): Deferred<Response<Result>>

// variable idbar en la ruta de la url => @Path
@GET("bar/{idbar}")
fun getDataBar(@Path("idbar") idbar: Int): Deferred<Response<Result>>

// nick y pass variables sueltas en la url?nick=paco&pass=paco => @Query
@GET("usuario")
fun getDataUsuarioPorNickPass(
       @Query("nick") nick: String,
       @Query("pass") pass: String): Deferred<Response<Result>>

// post con objeto => @Body
@POST("usuario")
fun saveUsuario(@Body usuario: Usuario): Deferred<Response<Result>>

// post con variables sueltas => @Field y Obligatorio @FormUrlEncoded
@FormUrlEncoded
@POST("bar/{idbar}/puntos")
fun savePuntos(@Path("idbar") idbar: Int,
              @Field("idusuario") idusuario: Int,
              @Field("puntos") puntos: Int): Deferred<Response<Result>>

// ……..   resto de métodos de la interfaz ………..
}

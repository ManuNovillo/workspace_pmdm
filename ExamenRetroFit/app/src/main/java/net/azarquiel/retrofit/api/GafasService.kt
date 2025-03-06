package net.azarquiel.retrofit.api

import kotlinx.coroutines.Deferred
import net.azarquiel.retrofit.model.Comentario
import retrofit2.Response
import retrofit2.http.*
import net.azarquiel.retrofit.model.Result
import net.azarquiel.retrofit.model.Usuario

/**
 * Created by Paco Pulido.
 */
interface GafasService {
    @GET("marcas")
    fun getMarcas(): Deferred<Response<Result>>

    @GET("marca/{idmarca}/gafas")
    fun getGafasByMarca(@Path("idmarca") idmarca: String): Deferred<Response<Result>>

    @GET("gafa/{idgafa}/comentarios")
    fun getComentariosByGafa(@Path("idgafa") idgafa: String): Deferred<Response<Result>>

    @GET("usuario")
    fun getDataUsuarioPorNickPass(
        @Query("nick") nick: String,
        @Query("pass") pass: String
    ): Deferred<Response<Result>>

    @POST("usuario")
    fun saveUsuario(@Body usuario: Usuario): Deferred<Response<Result>>

    @POST("gafa/{idgafa}/comentario")
    fun saveComentario(@Path("idgafa") idgafa: String, @Body comentario: Comentario): Deferred<Response<Result>>


}

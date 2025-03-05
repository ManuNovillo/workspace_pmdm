package net.azarquiel.chistesbien.api

import kotlinx.coroutines.Deferred
import net.azarquiel.chistesbien.model.Chiste
import net.azarquiel.chistesbien.model.Punto
import retrofit2.Response
import retrofit2.http.*
import net.azarquiel.chistesbien.model.Result
import net.azarquiel.chistesbien.model.Usuario

interface ChisteService {
    @GET("categorias")
    fun getCategorias(): Deferred<Response<Result>>

    @GET("categoria/{idcategoria}/chistes")
    fun getChistesByCategoria(@Path("idcategoria") idcategoria: String): Deferred<Response<Result>>

    @GET("usuario")
    fun getUsuario(@Query("nick") nick: String, @Query("pass") pass: String): Deferred<Response<Result>>

    @GET("chiste/{idchiste}/avgpuntos")
    fun getAvgPuntos(@Path("idchiste") idchiste: String): Deferred<Response<Result>>

    @POST("usuario")
    fun saveUsuario(@Body usuario: Usuario): Deferred<Response<Result>>

    @POST("chiste")
    fun saveChiste(@Body chiste: Chiste): Deferred<Response<Result>>

    @POST("chiste/{idchiste}/punto")
    @FormUrlEncoded
    fun puntuarChiste(@Path("idchiste") idchiste: String, @Body punto: Punto): Deferred<Response<Result>>
}
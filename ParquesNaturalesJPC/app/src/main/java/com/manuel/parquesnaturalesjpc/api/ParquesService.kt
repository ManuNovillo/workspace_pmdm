package com.manuel.parquesnaturalesjpc.api

import com.manuel.parquesnaturalesjpc.model.Result
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface ParquesService {

    @GET("comunidades")
    fun getComunidades(): Deferred<Response<Result>>

    @GET("parques")
    fun getParques(): Deferred<Response<Result>>

    @GET("comunidad/{idcomunidad}/parques")
    fun getParquesByComunidad(@Path("idcomunidad") idcomunidad: Int): Deferred<Response<Result>>

    @GET("parque/{idparque}/imagenes")
    fun getImagenesByParque(@Path("idparque") idparque: Int): Deferred<Response<Result>>

    @PATCH("parque/{idparque}/like")
    fun darLike(@Path("idparque") idparque: Int): Deferred<Response<Result>>
}

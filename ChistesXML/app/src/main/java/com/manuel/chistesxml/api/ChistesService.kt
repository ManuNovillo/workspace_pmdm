package com.manuel.chistesxml.api

import com.manuel.chistesxml.model.Chiste
import com.manuel.chistesxml.model.Punto
import com.manuel.chistesxml.model.Result
import com.manuel.chistesxml.model.Usuario
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Paco Pulido.
 * COPIAR Y PEGAR, CAMBIAR NOMBRE DOC 14
 * se cambia conforme a la api
 */
interface ChistesService {
    // No necesita nada para trabajar TENDRA LA URL BASE , INCLUIDA LA BARRA PRIMERA SIEMPRE
    @GET("categorias")
    fun getCategorias(): Deferred<Response<Result>> //devuelve un json con todas las categorias
    // (que esos datos estan en un objeto result), livedata con corrutina


    //vienen en la propia URL
    // variable idbar en la ruta de la url => @Path
    //obtiene los chistes, en la propia url le damos el id de la categoria y eso luego se declara abajo
    @GET("categoria/{idcategoria}/chistes")
    fun getChistesByCategoria(@Path("idcategoria") idcategoria: String): Deferred<Response<Result>> // {idcategoria} @ path porque viene en la URL


    //para hacer login
    //variables sueltas por get
    //mediante el método get en URL esas variables con @Query las variables despues de la ?
    // nick y pass variables sueltas en la url?nick=paco&pass=paco => @Query
    @GET("usuario")
    fun getDataUsuarioPorNickPass(
        @Query("nick") nick: String,
        @Query("pass") pass: String
    ): Deferred<Response<Result>>


    // post con objeto => @Body, cuando los datos van todos juntos como un objeto @Body
    @POST("usuario")
    fun saveUsuario(@Body usuario: Usuario): Deferred<Response<Result>>


    // post con objeto => @Body, cuando los datos van todos juntos como un objeto @Body
    @POST("chiste")
    fun saveChiste(@Body chiste: Chiste): Deferred<Response<Result>>


    @GET("chiste/{idchiste}/avgpuntos")
    fun getAvgPuntos(@Path("idchiste") idchiste: String): Deferred<Response<Result>>


    //variables sueltas por post @Field FIJARSE EN COMO ESTA PUNTOS EN LA DB
    // post con variables sueltas => @Field y Obligatorio @FormUrlEncoded
    @FormUrlEncoded
    @POST("chiste/{idchiste}/punto")
    fun savePuntos(
        @Path("idchiste") idchiste: Int,
        @Body punto: Punto
    ): Deferred<Response<Result>>
}


package com.manuel.parquesnaturalesjpc.api

import com.manuel.parquesnaturalesjpc.model.Comunidad
import com.manuel.parquesnaturalesjpc.model.Imagen
import com.manuel.parquesnaturalesjpc.model.Parque

class MainRepository {

    val service = WebAccess.parquesService

    suspend fun getComunidades(): List<Comunidad> {
        val webResponse = service.getComunidades().await()
        if (webResponse.isSuccessful)
            return webResponse.body()!!.comunidades

        return emptyList()
    }

    suspend fun getParques(): List<Parque> {
        val webResponse = service.getParques().await()
        if (webResponse.isSuccessful)
            return webResponse.body()!!.parques

        return emptyList()
    }

    suspend fun getParquesByComunidad(idCiudad: Int): List<Parque> {
        val webResponse = service.getParquesByComunidad(idCiudad).await()
        if (webResponse.isSuccessful)
            return webResponse.body()!!.parques

        return emptyList()
    }

    suspend fun getImagenesByParque(idParque: Int): List<Imagen> {
        val webResponse = service.getImagenesByParque(idParque).await()
        if (webResponse.isSuccessful)
            return webResponse.body()!!.imagenes

        return emptyList()
    }

    suspend fun darLike(idParque: Int): String? {
        val webResponse = service.darLike(idParque).await()
        if (webResponse.isSuccessful)
            return webResponse.body()!!.msg

        return null
    }
}

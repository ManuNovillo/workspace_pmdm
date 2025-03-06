package net.azarquiel.retrofit.api

import net.azarquiel.retrofit.model.Comentario
import net.azarquiel.retrofit.model.Gafa
import net.azarquiel.retrofit.model.Marca
import net.azarquiel.retrofit.model.Usuario

class MainRepository() {
    val service = WebAccess.gafasService

    suspend fun getMarcas(): List<Marca> {
        val webResponse = service.getMarcas().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.marcas
        }
        return emptyList()
    }

    suspend fun getGafasByMarca(idmarca: String): List<Gafa> {
        val webResponse = service.getGafasByMarca(idmarca).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.gafas
        }
        return emptyList()
    }

    suspend fun getComentariosByGafa(idGafa: String): List<Comentario> {
        val webResponse = service.getComentariosByGafa(idGafa).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.comentarios
        }
        return emptyList()
    }

    suspend fun getDataUsuarioPorNickPass(nick: String, pass: String): Usuario? {
        val webResponse = service.getDataUsuarioPorNickPass(nick, pass).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }

    suspend fun saveUsuario(usuario: Usuario): Usuario? {
        val webResponse = service.saveUsuario(usuario).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }

    suspend fun saveComentario(idgafa: String, comentario: Comentario): Comentario? {
        val webResponse = service.saveComentario(idgafa, comentario).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.comentario
        }
        return null
    }
}

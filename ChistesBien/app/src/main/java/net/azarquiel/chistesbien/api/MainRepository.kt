package net.azarquiel.chistesbien.api

import net.azarquiel.chistesbien.model.Categoria
import net.azarquiel.chistesbien.model.Chiste
import net.azarquiel.chistesbien.model.Punto
import net.azarquiel.chistesbien.model.Usuario

class MainRepository() {
   val service = WebAccess.chisteService

    suspend fun getCategorias(): List<Categoria> {
        val webResponse = service.getCategorias().await()
        if (webResponse.isSuccessful) {
           return webResponse.body()!!.categorias
        }
        return emptyList()
    }

    suspend fun getChistesByCategoria(idCategoria:String): List<Chiste> {
        val webResponse = service.getChistesByCategoria(idCategoria).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.chistes
        }
        return emptyList()
    }

    suspend fun getUsuario(nick:String, pass:String): Usuario? {
        val webResponse = service.getUsuario(nick, pass).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }

    suspend fun getAvgPuntos(idchiste:String): Int {
        val webResponse = service.getAvgPuntos(idchiste).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.avg
        }
        return 0
    }

    suspend fun saveUsuario(usuario: Usuario): Usuario? {
       val webResponse = service.saveUsuario(usuario).await()
       if (webResponse.isSuccessful) {
           return webResponse.body()!!.usuario
       }
       return null
    }

    suspend fun saveChiste(chiste: Chiste): Chiste? {
        val webResponse = service.saveChiste(chiste).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.chiste
        }
        return null
    }

    suspend fun puntuarChiste(idChiste:String, punto: Punto): Punto? {
        val webResponse = service.puntuarChiste(idChiste, punto).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.punto
        }
        return null
    }
}
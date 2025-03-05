package net.azarquiel.retrofit.api

class MainRepository() {
   val service = WebAccess.barService

suspend fun getPelis(): List<Movie> {
   val webResponse = service.getPelis().await()
   if (webResponse.isSuccessful) {
       return webResponse.body()!!.results
   }
   return emptyList()
}


suspend fun saveComentario(idmovie:String, comentario:Comentario
): Comentario? {
   val webResponse = service.saveComentario(idmovie, comentario).await()
   if (webResponse.isSuccessful) {
       return webResponse.body()!!.comentario
   }
   return null
}


// ……..   resto de métodos del Repository ………..
 }

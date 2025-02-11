import com.manuel.chistesxml.api.WebAccess
import com.manuel.chistesxml.model.Categoria
import com.manuel.chistesxml.model.Chiste
import com.manuel.chistesxml.model.Punto
import com.manuel.chistesxml.model.Usuario

class MainRepository() {
    val service =
        WebAccess.chistesService //para que con esa interfaz podamos llamar a los servicios


    suspend fun getCategorias(): List<Categoria> {
        val webResponse = service.getCategorias()
            .await() //sincronizamos por corrutina, necesario el suspend igual porque esta ejecutandose un hilo
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.categorias //con .body se accede al result, y lo que habia en result en este caso queremos las categorias
        }
        return emptyList() //si no lo consigue devolvemos una lista vacia
    }


    suspend fun getChistesByCategoria(idcategoria: String): List<Chiste> {
        val webResponse = service.getChistesByCategoria(idcategoria)
            .await() //sincronizamos por corrutina, necesario el suspend igual porque esta ejecutandose un hilo
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.chistes //con .body se accede al result, y lo que habia en result en este caso queremos las categorias
        }
        return emptyList() //si no lo consigue devolvemos una lista vacia
    }


    suspend fun getAvgPuntos(idchiste: String): String {
        val webResponse = service.getAvgPuntos(idchiste)
            .await() //sincronizamos por corrutina, necesario el suspend igual porque esta ejecutandose un hilo
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.avg //con .body se accede al result, y lo que habia en result en este caso queremos las categorias
        }
        return "" //si no lo consigue devolvemos una lista vacia
    }


    suspend fun getDataUsuarioPorNickPass(nick: String, pass: String): Usuario? {
        val webResponse = service.getDataUsuarioPorNickPass(nick, pass)
            .await() //sincronizamos por corrutina, necesario el suspend igual porque esta ejecutandose un hilo
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario //con .body se accede al result, y lo que habia en result en este caso queremos las categorias
        }
        return null //si no lo consigue devolvemos una lista vacia
    }

    suspend fun saveChiste(chiste: Chiste): Chiste? { //chiste que se inserta


        val webResponse = service.saveChiste(chiste)
            .await() //le pasamos parametro que nos ha dado (chiste a insertar)
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.chiste
        }
        return null
    }


    suspend fun saveUsuario(usuario: Usuario): Usuario? { //chiste que se inserta


        val webResponse = service.saveUsuario(usuario)
            .await() //le pasamos parametro que nos ha dado (chiste a insertar)
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }


    suspend fun savePuntos(idchiste: String, punto: Punto): Punto? {

        val webResponse = service.savePuntos(idchiste, punto)
            .await() //le pasamos parametro que nos ha dado (chiste a insertar)
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.punto
        }
        return null
    }
}

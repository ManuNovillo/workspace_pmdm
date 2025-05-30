package net.azarquiel.examenmanuelnovillo.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.io.*

/**
 * Autor: Paco Pulido
 */

class Util {
    companion object {
        private lateinit var context: Context

        fun inyecta(context: Context, fileDB:String) {
            Companion.context = context
            if (!File("/data/data/${context.packageName}/databases/${fileDB}").exists()) {
                Toast.makeText(context,"Cargando datos....", Toast.LENGTH_LONG).show()
                copiarFile(fileDB)
            }
        }
        private fun copiarFile(fileDB:String) {
            creaDirectorio()
            copiar(fileDB)
        }



        private fun creaDirectorio() {
            val file = File("/data/data/${context.packageName}/databases")
            file.mkdir()
        }

        private fun copiar(file: String) {
            val ruta = ("/data/data/${context.packageName}/databases/$file")
            val input: InputStream?
            val output: OutputStream?
            try {
                input = context.assets.open(file)
                output = FileOutputStream(ruta)
                copyFile(input, output)
                input.close()
                output.close()
            } catch (e: IOException) {
                Log.e("Traductor", "Fallo en la copia del archivo desde el asset", e)
            }
        }

        private fun copyFile(input: InputStream?, output: OutputStream) {
            val buffer = ByteArray(1024)
            var read: Int
            read = input!!.read(buffer)
            while (read != -1) {
                output.write(buffer, 0, read)
                read = input.read(buffer)
            }
        }
    }
}
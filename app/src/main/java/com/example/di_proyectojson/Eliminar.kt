package com.example.di_proyectojson

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.eliminar_pelicula.*
import kotlinx.android.synthetic.main.insertar_pelicula.*
import kotlinx.android.synthetic.main.insertar_pelicula.tftitulo
import java.io.IOException
import java.net.URL

class Eliminar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.eliminar_pelicula)

        btEliminar.setOnClickListener{
            eliminar()
            finish()
        }
    }

    fun eliminar(){
        if ((tftitulo2.length()>0)) {
            val url = "http://iesayala.ddns.net/jomiferse/json/eliminar.php/?titulo=" + tftitulo2.text.toString()
            leerUrl(url)
            Toast.makeText(this, "Película eliminada correctamente", Toast.LENGTH_LONG).show()
        }  else {
            Toast.makeText(this, "Error al añadir película", Toast.LENGTH_LONG).show()
        }
    }

    private fun leerUrl(urlString:String): String{

        val response = try {
            URL(urlString)
                .openStream()
                .bufferedReader()
                .use { it.readText() }
        } catch (e: IOException) {
            "Error with ${e.message}."
        }
        return response
    }
}
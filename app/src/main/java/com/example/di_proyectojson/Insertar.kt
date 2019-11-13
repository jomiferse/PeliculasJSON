package com.example.di_proyectojson

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.insertar_pelicula.*
import java.io.IOException
import java.net.URL

class Insertar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insertar_pelicula)

        btInsertar.setOnClickListener{
            insertar()
            finish()
        }
    }

    fun insertar(){
        if ((tftitulo.length()>0) and (tfcategoria.length()>0) and (tfdescripcion.length()>0) and (tfminiatura.length()>0)) {
            val url = "http://iesayala.ddns.net/jomiferse/json/insertar.php/?titulo=" + tftitulo.text.toString() + "&categoria=" + tfcategoria.text.toString() + "&descripcion=" + tfdescripcion.text.toString() + "&miniatura=" + tfminiatura.text.toString()
            leerUrl(url)
            Toast.makeText(this, "Película añadida correctamente", Toast.LENGTH_LONG).show()
        } else {
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
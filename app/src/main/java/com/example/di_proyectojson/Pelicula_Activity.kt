package com.example.di_proyectojson

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class Pelicula_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peliculas_)

        var titulo = findViewById(R.id.txttitulo) as TextView
        var descripcion = findViewById(R.id.txtDescripcion) as TextView
        var categoria = findViewById(R.id.txtCategoria) as TextView
        var imagen = findViewById(R.id.pelicula_miniatura) as ImageView

        val intent = intent
        val iTitulo = intent.extras!!.getString("Titulo")
        val iDescripcion = intent.extras!!.getString("Descripcion")
        val iImagen = intent.extras!!.getString("Miniatura")
        val iCategoria = intent.extras!!.getString("Categoria")


        titulo.setText(iTitulo)
        descripcion.setText(iDescripcion)
        Picasso.with(this).load(iImagen).into(imagen)
        categoria.setText(iCategoria)

    }

}
package com.example.di_proyectojson

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.text.method.ScrollingMovementMethod
import com.google.gson.Gson
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuCompat
import androidx.recyclerview.widget.*
import com.squareup.picasso.Picasso
import java.io.IOException
import java.net.URL
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    val listaPeliculas = ArrayList<Pelicula>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        cargarDatos();
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        MenuCompat.setGroupDividerEnabled(menu, true);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.insertar -> {
                insertar()
                return true
            }
            R.id.recargar -> {
                recargar()
                return true
            }
            R.id.eliminar -> {
                eliminar()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun recargar() {
        cargarDatos()
    }

    fun insertar() {
        val i = Intent(this, Insertar::class.java)
        startActivity(i)
    }

    fun eliminar() {
        val i = Intent(this, Eliminar::class.java)
        startActivity(i)
    }

    fun cargarDatos() {
        listaPeliculas.clear()
        val gson = Gson()
        try {
            val json = leerUrl("http://iesayala.ddns.net/jomiferse/json/ConsultaSQL.php")
            val pelicula = gson.fromJson(json, PeliculasArray::class.java)

            for (item in pelicula.peliculas!!.iterator()) {
                Log.d("RESULTADO", item.titulo)
                listaPeliculas.add(Pelicula(item.titulo,item.categoria,item.descripcion,item.miniatura))
            }
            val myrv = findViewById(R.id.recyclerview_id) as RecyclerView
            val myAdapter = AdaptadorRecyclerView(this,listaPeliculas)
            myrv.setLayoutManager(GridLayoutManager(this, 3))
            myrv.setAdapter(myAdapter)
        }
        catch (e: Exception){
            Log.d("RESULTADO", "error")
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
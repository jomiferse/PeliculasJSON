package com.example.di_proyectojson

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdaptadorRecyclerView(var mContext: Context,var listaP: ArrayList<Pelicula>): RecyclerView.Adapter<AdaptadorRecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mInflater = LayoutInflater.from(mContext).inflate(R.layout.cardview_item_peliculas,parent,false)
        return ViewHolder(mInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, posicion: Int) {
        holder.titulo_pelicula.setText(listaP[posicion].titulo)
        Picasso.with(mContext).load(listaP[posicion].miniatura).into(holder.miniatura_pelicula)
        holder.cardView.setOnClickListener(View.OnClickListener {
            val intent = Intent(mContext, Pelicula_Activity::class.java)

            intent.putExtra("Titulo", listaP[posicion].titulo)
            intent.putExtra("Descripcion", listaP[posicion].descripcion)
            intent.putExtra("Miniatura", listaP[posicion].miniatura)
            intent.putExtra("Categoria",listaP[posicion].categoria)
            mContext.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return listaP.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var titulo_pelicula: TextView
        internal var miniatura_pelicula: ImageView
        internal var cardView: CardView

        init {
            titulo_pelicula = itemView.findViewById(R.id.pelicula_titulo_id) as TextView
            miniatura_pelicula = itemView.findViewById(R.id.pelicula_imagen_id) as ImageView
            cardView = itemView.findViewById(R.id.cardview_id) as CardView
        }
    }
}
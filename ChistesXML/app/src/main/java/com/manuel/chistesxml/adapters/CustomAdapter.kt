package com.manuel.chistesxml.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manuel.chistesxml.R
import com.manuel.chistesxml.model.Categoria
import com.squareup.picasso.Picasso

class CustomAdapter(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataList: List<Categoria> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setCategorias(categorias: List<Categoria>) {
        this.dataList = categorias
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Categoria){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val categoriasCellImage: ImageView = itemView.findViewById(R.id.categoriasCellImage)
            val categoriasCellText: TextView = itemView.findViewById(R.id.categoriasCellText)
            //....

            categoriasCellText.text = dataItem.nombre
            //....

            // foto de internet a traves de Picasso
            Picasso.get()
                .load("http://www.ies-azarquiel.es/paco/apichistes/img/${dataItem.id}.png")
                .into(categoriasCellImage)

            itemView.tag = dataItem

        }

    }
}
package com.marianoroces.norris.tp3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marianoroces.norris.tp3.R
import com.marianoroces.norris.tp3.model.Person

class PoorPersonAdapter(private val listaPersonas: ArrayList<Person>) :
    RecyclerView.Adapter<PoorPersonAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val titulo: TextView
        val ocupacion: TextView
        val ingresos: TextView

        init {
            titulo = view.findViewById(R.id.card_poverty_title)
            ocupacion = view.findViewById(R.id.card_poverty_occupation)
            ingresos = view.findViewById(R.id.card_poverty_income)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_poverty_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titulo.setText(listaPersonas[position].apellido+", "+listaPersonas[position].nombre)
        holder.ocupacion.setText("Ocupacion: "+listaPersonas[position].ocupacion)
        holder.ingresos.setText("Ingresos: $"+listaPersonas[position].ingresos.toString())
    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }
}
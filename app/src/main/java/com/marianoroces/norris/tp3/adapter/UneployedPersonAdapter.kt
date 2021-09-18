package com.marianoroces.norris.tp3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marianoroces.norris.tp3.R
import com.marianoroces.norris.tp3.model.Person

class UneployedPersonAdapter(private val listaPersonas: ArrayList<Person>) :
    RecyclerView.Adapter<UneployedPersonAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            val titulo: TextView
            val ocupacion: TextView
            val fechaNacimiento: TextView

            init {
                titulo = view.findViewById(R.id.card_unemployed_title)
                ocupacion = view.findViewById(R.id.card_unemployed_occupation)
                fechaNacimiento = view.findViewById(R.id.card_unemployed_birth_date)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_unemployed_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titulo.setText(listaPersonas[position].apellido+", "+listaPersonas[position].nombre)
        holder.ocupacion.setText("Ocupacion: "+listaPersonas[position].ocupacion)
        holder.fechaNacimiento.setText("Fecha de nacimiento: "+listaPersonas[position].fechaNacimiento)
    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }
}
package com.marianoroces.norris.tp3.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.marianoroces.norris.tp3.R
import com.marianoroces.norris.tp3.model.Person
import com.marianoroces.norris.tp3.view.ModifyActivity
import com.marianoroces.norris.tp3.viewmodel.PersonViewModel

class FullPersonAdapter(
    private val listaPersonas: ArrayList<Person>,
    private val personViewModel: PersonViewModel,
    private val context: Context
) :
    RecyclerView.Adapter<FullPersonAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val titulo: TextView
        val dni: TextView
        val nacimiento: TextView
        val sexo: TextView
        val direccion: TextView
        val ocupacion: TextView
        var borrar: MaterialButton
        var modificar: MaterialButton

        init {
            titulo = view.findViewById(R.id.card_title)
            dni = view.findViewById(R.id.card_dni)
            nacimiento = view.findViewById(R.id.card_birth_date)
            sexo = view.findViewById(R.id.card_sex)
            direccion = view.findViewById(R.id.card_address_phone)
            ocupacion = view.findViewById(R.id.card_occupation_income)
            borrar = view.findViewById(R.id.card_delete)
            modificar = view.findViewById(R.id.card_modify)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_full_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.titulo.setText(listaPersonas[position].apellido + ", " + listaPersonas[position].nombre)
        holder.dni.setText(listaPersonas[position].tipoDni + ": " + listaPersonas[position].dni)
        holder.nacimiento.setText(listaPersonas[position].fechaNacimiento)
        holder.sexo.setText(listaPersonas[position].sexo)
        holder.direccion.setText("Direccion: " + listaPersonas[position].direccion + " - Tel: " + listaPersonas[position].telefono.toString())
        holder.ocupacion.setText("Ocupacion: " + listaPersonas[position].ocupacion + " - Ingresos: $" + listaPersonas[position].ingresos.toString())

        holder.borrar.setOnClickListener {

            if (personViewModel.deletePerson(context, listaPersonas[position].id)) {
                listaPersonas.removeAt(position)
                notifyDataSetChanged()
                Toast.makeText(context, "Borrado correctamente", Toast.LENGTH_LONG).show()
            }
        }

        holder.modificar.setOnClickListener {

            val intent = Intent(context, ModifyActivity::class.java)
            intent.putExtra("persona", listaPersonas[position])
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }
}
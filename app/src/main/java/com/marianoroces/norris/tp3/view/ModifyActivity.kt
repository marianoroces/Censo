package com.marianoroces.norris.tp3.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.marianoroces.norris.tp3.R
import com.marianoroces.norris.tp3.enums.Occupation
import com.marianoroces.norris.tp3.enums.Sex
import com.marianoroces.norris.tp3.model.Person
import com.marianoroces.norris.tp3.viewmodel.PersonViewModel
import org.w3c.dom.Text

class ModifyActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var title: TextView
    lateinit var direccion: TextInputEditText
    lateinit var telefono: TextInputEditText
    lateinit var ingresos: TextInputEditText
    lateinit var comboSexo: AutoCompleteTextView
    lateinit var comboOcupacion: AutoCompleteTextView
    lateinit var comboSexoLayout: TextInputLayout
    lateinit var comboOcupacionLayout: TextInputLayout
    lateinit var guardar: Button
    lateinit var cancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)

        initializeElements()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val personaVM: PersonViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)
        var persona: Person = intent.extras?.getSerializable("persona") as Person
        loadData(persona)

        comboSexo.setOnItemClickListener { parent, view, position, id ->
            persona.sexo = parent.getItemAtPosition(position).toString()
        }

        comboOcupacion.setOnItemClickListener { parent, view, position, id ->
            persona.ocupacion = parent.getItemAtPosition(position).toString()
        }

        cancelar.setOnClickListener {
            Toast.makeText(this, "No se ha realizado ningun cambio", Toast.LENGTH_LONG).show()
            finish()
        }

        guardar.setOnClickListener {

            persona.direccion = direccion.text.toString()
            persona.telefono = telefono.text.toString()
            persona.ingresos = ingresos.text.toString().toInt()

            if (validateInputs()) {
                if (personaVM.updatePerson(this, persona)) {
                    Toast.makeText(this, "Persona actualizada correctamente", Toast.LENGTH_LONG)
                        .show()
                    finish()
                } else
                    Toast.makeText(this, "Error al actualizar persona", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initializeElements() {
        toolbar = findViewById(R.id.m_toolbar)
        title = findViewById(R.id.m_title)
        direccion = findViewById(R.id.m_address)
        telefono = findViewById(R.id.m_phone)
        ingresos = findViewById(R.id.m_income)
        comboSexo = findViewById(R.id.m_sex_selector)
        comboOcupacion = findViewById(R.id.m_occupation_selector)
        guardar = findViewById(R.id.m_save)
        cancelar = findViewById(R.id.m_cancel)
        comboSexoLayout = findViewById(R.id.m_sex_layout)
        comboOcupacionLayout = findViewById(R.id.m_occupation_layout)

        val adapterSexo = ArrayAdapter(this, R.layout.list_item, Sex.values())
        comboSexo.setAdapter(adapterSexo)

        val adapterOcupacion = ArrayAdapter(this, R.layout.list_item, Occupation.values())
        comboOcupacion.setAdapter(adapterOcupacion)
    }

    private fun loadData(person: Person) {

        title.text = person.apellido + ", " + person.nombre
        direccion.setText(person.direccion)
        telefono.setText(person.telefono)
        ingresos.setText(person.ingresos.toString())
    }

    private fun validateInputs(): Boolean {

        var result: Boolean = true

        if (comboSexo.text.toString() == "") {
            comboSexoLayout.error = "Seleccionar"
            result = false
        } else
            comboSexoLayout.error = null

        if (comboOcupacion.text.toString() == "") {
            comboOcupacionLayout.error = "Seleccionar"
            result = false
        } else
            comboOcupacionLayout.error = null

        return result
    }
}
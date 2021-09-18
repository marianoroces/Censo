package com.marianoroces.norris.tp3.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.marianoroces.norris.tp3.R
import com.marianoroces.norris.tp3.enums.Occupation
import com.marianoroces.norris.tp3.enums.Sex
import com.marianoroces.norris.tp3.enums.TypesId
import com.marianoroces.norris.tp3.viewmodel.PersonViewModel
import java.util.*

class ReportActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var comboDni: AutoCompleteTextView
    lateinit var comboSexo: AutoCompleteTextView
    lateinit var comboOcupacion: AutoCompleteTextView
    lateinit var dni: TextInputEditText
    lateinit var nombre: TextInputEditText
    lateinit var apellido: TextInputEditText
    lateinit var fechaNacimiento: TextInputEditText
    lateinit var direccion: TextInputEditText
    lateinit var telefono: TextInputEditText
    lateinit var ingresos: TextInputEditText
    lateinit var comboDniLayout: TextInputLayout
    lateinit var comboSexoLayout: TextInputLayout
    lateinit var dniLayout: TextInputLayout
    lateinit var nombreLayout: TextInputLayout
    lateinit var apellidoLayout: TextInputLayout
    lateinit var fechaNacimientoLayout: TextInputLayout
    lateinit var ingresosLayout: TextInputLayout
    lateinit var generar: Button

    lateinit var dniInput: String
    lateinit var sexoInput: String
    lateinit var ocupacionInput: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        initializeElements()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val personaVM: PersonViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)

        fechaNacimiento.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Seleccionar fecha")
                .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
                .build()

            datePicker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance()
                calendar.time = Date(it)

                if (validateDate(calendar) == -1) {
                    val fecha = (calendar.get(Calendar.DAY_OF_MONTH) + 1).toString() + "/" +
                            (calendar.get(Calendar.MONTH) + 1).toString() + "/" +
                            calendar.get(Calendar.YEAR).toString()

                    fechaNacimientoLayout.error = null
                    fechaNacimiento.setText(fecha)
                } else {
                    Toast.makeText(
                        this,
                        "Ingrese una fecha de nacimiento valida",
                        Toast.LENGTH_LONG
                    ).show()

                    fechaNacimientoLayout.error = "Completar"
                    fechaNacimiento.setText("")
                }
            }

            datePicker.show(supportFragmentManager, "DATE_PICKER")
        }

        comboDni.setOnItemClickListener { parent, _, position, _ ->
            dniInput = parent.getItemAtPosition(position).toString()
        }

        comboSexo.setOnItemClickListener { parent, _, position, _ ->
            sexoInput = parent.getItemAtPosition(position).toString()
        }

        comboOcupacion.setOnItemClickListener { parent, _, position, _ ->
            ocupacionInput = parent.getItemAtPosition(position).toString()
        }

        generar.setOnClickListener {

            if (validateInputs()) {
                if (personaVM.savePerson(
                        dniInput,
                        dni.text.toString(),
                        nombre.text.toString(),
                        apellido.text.toString(),
                        fechaNacimiento.text.toString(),
                        sexoInput,
                        direccion.text.toString(),
                        telefono.text.toString(),
                        ocupacionInput,
                        ingresos.text.toString().toInt(),
                        this
                    )
                ) {
                    Toast.makeText(this, "Persona guardada exitosamente", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al guardar persona", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initializeElements() {
        comboDni = findViewById(R.id.r_dni_selector)
        comboSexo = findViewById(R.id.r_sex_selector)
        comboOcupacion = findViewById(R.id.r_occupation_selector)
        toolbar = findViewById(R.id.r_toolbar)
        nombre = findViewById(R.id.r_first_name)
        dni = findViewById(R.id.r_dni)
        apellido = findViewById(R.id.r_last_name)
        fechaNacimiento = findViewById(R.id.r_birth_date)
        direccion = findViewById(R.id.r_address)
        telefono = findViewById(R.id.r_phone)
        ingresos = findViewById(R.id.r_income)
        generar = findViewById(R.id.r_generate)
        fechaNacimientoLayout = findViewById(R.id.r_birth_date_layout)
        comboDniLayout = findViewById(R.id.r_dni_selector_layout)
        comboSexoLayout = findViewById(R.id.r_sex_selector_layout)
        dniLayout = findViewById(R.id.r_dni_layout)
        nombreLayout = findViewById(R.id.r_first_name_layout)
        apellidoLayout = findViewById(R.id.r_last_name_layout)
        ingresosLayout = findViewById(R.id.r_income_layout)

        val adapterDni = ArrayAdapter(applicationContext, R.layout.list_item, TypesId.values())
        comboDni.setAdapter(adapterDni)

        val adapterSexo = ArrayAdapter(applicationContext, R.layout.list_item, Sex.values())
        comboSexo.setAdapter(adapterSexo)

        val adapterOcupacion = ArrayAdapter(applicationContext, R.layout.list_item, Occupation.values())
        comboOcupacion.setAdapter(adapterOcupacion)
    }

    private fun validateInputs(): Boolean {

        var result: Boolean = true

        if (comboDni.text.toString().equals("Select")) {
            comboDniLayout.error = "Seleccionar"
            result = false
        } else
            comboDniLayout.error = null

        if (dni.text.toString().equals("")) {
            dniLayout.error = "Completar"
            result = false
        } else
            dniLayout.error = null

        if (nombre.text.toString().equals("")) {
            nombreLayout.error = "Completar"
            result = false
        } else
            nombreLayout.error = null

        if (apellido.text.toString().equals("")) {
            apellidoLayout.error = "Completar"
            result = false
        } else
            apellidoLayout.error = null

        if (fechaNacimiento.text.toString().equals("")) {
            fechaNacimientoLayout.error = "Completar"
            result = false
        } else
            fechaNacimientoLayout.error = null

        if (comboSexo.text.toString().equals("Sexo")) {
            comboSexoLayout.error = "Seleccionar"
            result = false
        } else
            comboSexoLayout.error = null

        if (ingresos.text.toString().equals("")) {
            ingresosLayout.error = "Completar"
            result = false
        } else
            ingresosLayout.error = null

        return result
    }

    private fun validateDate(date: Calendar): Int {

        val aux: Calendar = Calendar.getInstance()

        return date.compareTo(aux)
    }
}
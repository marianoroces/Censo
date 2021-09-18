package com.marianoroces.norris.tp3.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.marianoroces.norris.tp3.dao.DbHelper
import com.marianoroces.norris.tp3.model.Person
import kotlin.collections.ArrayList

class PersonViewModel() : ViewModel() {

    fun savePerson(
        tipo: String,
        dni: String,
        nombre: String,
        apellido: String,
        fecha: String,
        sexo: String,
        direccion: String,
        telefono: String,
        ocupacion: String,
        ingresos: Int,
        context: Context
    ): Boolean {
        val db: DbHelper = DbHelper(context, null)

        return db.savePerson(
            Person(
                0,
                tipo,
                dni,
                nombre,
                apellido,
                fecha,
                sexo,
                direccion,
                telefono,
                ocupacion,
                ingresos
            )
        )
    }

    fun getAll(context: Context): ArrayList<Person> {
        val db: DbHelper = DbHelper(context, null)

        return db.getAll()
    }

    fun getDistribution(context: Context): Array<Int> {
        val db: DbHelper = DbHelper(context, null)

        return db.countDistribution()
    }

    fun getPoorPeople(context: Context): ArrayList<Person> {
        val db: DbHelper = DbHelper(context, null)

        return db.getPoorPeople()
    }

    fun getUnemployedPeople(context: Context): ArrayList<Person> {
        val db: DbHelper = DbHelper(context, null)

        return db.getUnemployed()
    }

    fun deletePerson(context: Context, id: Int): Boolean {
        val db: DbHelper = DbHelper(context, null)

        return db.deletePerson(id)
    }

    fun updatePerson(context: Context, person: Person): Boolean{
        val db: DbHelper = DbHelper(context, null)

        return db.updatePerson(person)
    }
}
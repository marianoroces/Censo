package com.marianoroces.norris.tp3.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.marianoroces.norris.tp3.enums.Occupation
import com.marianoroces.norris.tp3.enums.Sex
import com.marianoroces.norris.tp3.model.Person
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "censos.db"
        private val DATABASE_VERSION = 1

        val TABLE_NAME = "personas"
        val COLUMN_ID = "id"
        val COLUMN_TIPO = "tipo_dni"
        val COLUMN_DNI = "dni"
        val COLUMN_NOMBRE = "nombre"
        val COLUMN_APELLIDO = "apellido"
        val COLUMN_FECHA_NACIMIENTO = "fecha_nacimiento"
        val COLUMN_SEXO = "sexo"
        val COLUMN_DIRECCION = "direccion"
        val COLUMN_TELEFONO = "telefono"
        val COLUMN_OCUPACION = "ocupacion"
        val COLUMN_INGRESOS = "ingresos"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var createTable =
            ("CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_TIPO + " TEXT, " +
                    COLUMN_DNI + " TEXT, " +
                    COLUMN_NOMBRE + " TEXT, " +
                    COLUMN_APELLIDO + " TEXT, " +
                    COLUMN_FECHA_NACIMIENTO + " TEXT, " +
                    COLUMN_SEXO + " TEXT, " +
                    COLUMN_DIRECCION + " TEXT, " +
                    COLUMN_TELEFONO + " TEXT, " +
                    COLUMN_OCUPACION + " TEXT, " +
                    COLUMN_INGRESOS + " INTEGER ) ")

        db?.execSQL(createTable)

        migrateDatabase(
            db,
            "DNI",
            "38456789",
            "Martin",
            "Perez",
            "4/10/1994",
            "MASCULINO",
            "Santa Fe 2347",
            "155741369",
            "ESTUDIANTE",
            20000
        )
        migrateDatabase(
            db,
            "DNI",
            "33582104",
            "Carla",
            "Gomez",
            "25/2/1988",
            "FEMENINO",
            "Corrientes 624",
            "154289635",
            "EMPLEADO",
            70000
        )
        migrateDatabase(
            db,
            "DNI",
            "13504323",
            "Rosana",
            "Fernandez",
            "17/5/1959",
            "FEMENINO",
            "Rivadavia 8462",
            "156348025",
            "DESEMPLEADO",
            4500
        )
        migrateDatabase(
            db,
            "DNI",
            "40698521",
            "Lucia",
            "Nuñez",
            "23/7/2003",
            "FEMENINO",
            "Rivadavia 8462",
            "155287014",
            "DESEMPLEADO",
            0
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun updatePerson(persona: Person): Boolean {

        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_SEXO, persona.sexo)
        values.put(COLUMN_DIRECCION, persona.direccion)
        values.put(COLUMN_TELEFONO, persona.telefono)
        values.put(COLUMN_OCUPACION, persona.ocupacion)
        values.put(COLUMN_INGRESOS, persona.ingresos)

        return db.update(TABLE_NAME, values, COLUMN_ID + "=" + persona.id, null) > 0
    }

    fun savePerson(persona: Person): Boolean {
        try {
            val db = this.writableDatabase

            val values = ContentValues()
            values.put(COLUMN_TIPO, persona.tipoDni)
            values.put(COLUMN_DNI, persona.dni)
            values.put(COLUMN_NOMBRE, persona.nombre)
            values.put(COLUMN_APELLIDO, persona.apellido.uppercase())
            values.put(COLUMN_FECHA_NACIMIENTO, persona.fechaNacimiento)
            values.put(COLUMN_SEXO, persona.sexo)
            values.put(COLUMN_DIRECCION, persona.direccion)
            values.put(COLUMN_TELEFONO, persona.telefono)
            values.put(COLUMN_OCUPACION, persona.ocupacion)
            values.put(COLUMN_INGRESOS, persona.ingresos)

            db.insert(TABLE_NAME, null, values)

            return true
        } catch (e: Exception) {
            Log.d("DATABASE_ERROR", e.message.toString())
            return false
        }
    }

    fun getUnemployed(): ArrayList<Person> {

        val db = this.readableDatabase
        val listaPersonas: ArrayList<Person> = ArrayList()
        val query =
            "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_OCUPACION + " = '" + Occupation.DESEMPLEADO + "'"

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val fechaNacimiento = cursor.getString(
                    cursor.getColumnIndex(
                        COLUMN_FECHA_NACIMIENTO
                    )
                )

                val today: Calendar = Calendar.getInstance()
                val personBirthDate: Calendar = Calendar.getInstance()

                val format: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                personBirthDate.time = format.parse(fechaNacimiento)

                if (compareAge(personBirthDate, today)) {
                    val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                    val tipo = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO))
                    val dni = cursor.getString(cursor.getColumnIndex(COLUMN_DNI))
                    val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                    val apellido = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO))
                    val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                    val direccion = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION))
                    val telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
                    val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPACION))
                    val ingresos = cursor.getInt(cursor.getColumnIndex(COLUMN_INGRESOS))

                    listaPersonas.add(
                        Person(
                            id,
                            tipo,
                            dni,
                            nombre,
                            apellido,
                            fechaNacimiento,
                            sexo,
                            direccion,
                            telefono,
                            ocupacion,
                            ingresos
                        )
                    )
                }
            } while (cursor.moveToNext())
        }
        return listaPersonas
    }

    fun getAll(): ArrayList<Person> {

        val db = this.readableDatabase
        val listaPersonas: ArrayList<Person> = ArrayList()
        val query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_APELLIDO + " ASC"

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val tipo = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO))
                val dni = cursor.getString(cursor.getColumnIndex(COLUMN_DNI))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val apellido = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO))
                val fecha_nacimiento = cursor.getString(
                    cursor.getColumnIndex(
                        COLUMN_FECHA_NACIMIENTO
                    )
                )
                val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                val direccion = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION))
                val telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
                val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPACION))
                val ingresos = cursor.getInt(cursor.getColumnIndex(COLUMN_INGRESOS))

                listaPersonas.add(
                    Person(
                        id,
                        tipo,
                        dni,
                        nombre,
                        apellido,
                        fecha_nacimiento,
                        sexo,
                        direccion,
                        telefono,
                        ocupacion,
                        ingresos
                    )
                )
            } while (cursor.moveToNext())
        }
        return listaPersonas
    }

    fun countDistribution(): Array<Int> {

        val db = this.readableDatabase
        val queryMen = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_SEXO + " = '" + Sex.MASCULINO + "'"
        val queryWomen = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_SEXO + " = '" + Sex.FEMENINO + "'"

        val cursorMen = db.rawQuery(queryMen, null)
        val cursorWomen = db.rawQuery(queryWomen, null)

        return arrayOf(cursorMen.count, cursorWomen.count)
    }

    fun deletePerson(id: Int): Boolean {

        val db = this.writableDatabase

        return db.delete(TABLE_NAME, COLUMN_ID + "=" + id, null) > 0
    }

    fun getPoorPeople(): ArrayList<Person> {

        val db = this.readableDatabase
        val listaPersonas: ArrayList<Person> = ArrayList()
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_INGRESOS + " < 5000"

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val apellido = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO))
                val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPACION))
                val ingresos = cursor.getInt(cursor.getColumnIndex(COLUMN_INGRESOS))

                listaPersonas.add(
                    Person(
                        0,
                        "",
                        "",
                        nombre,
                        apellido,
                        "",
                        "",
                        "",
                        "",
                        ocupacion,
                        ingresos
                    )
                )
            } while (cursor.moveToNext())
        }
        return listaPersonas
    }

    private fun migrateDatabase(
        db: SQLiteDatabase?,
        tipo: String,
        dni: String,
        nombre: String,
        apellido: String,
        fecha: String,
        sexo: String,
        direccion: String,
        telefono: String,
        ocupacion: String,
        ingresos: Int
    ) {

        try {
            val values = ContentValues()

            values.put(COLUMN_TIPO, tipo)
            values.put(COLUMN_DNI, dni)
            values.put(COLUMN_NOMBRE, nombre)
            values.put(COLUMN_APELLIDO, apellido.uppercase())
            values.put(COLUMN_FECHA_NACIMIENTO, fecha)
            values.put(COLUMN_SEXO, sexo)
            values.put(COLUMN_DIRECCION, direccion)
            values.put(COLUMN_TELEFONO, telefono)
            values.put(COLUMN_OCUPACION, ocupacion)
            values.put(COLUMN_INGRESOS, ingresos)

            db?.insert(TABLE_NAME, null, values)
        } catch (e: Exception) {
            Log.d("ERROR_DATABASE", "ERROR INSERT PERSONA: " + e.message.toString())
        }
    }

    private fun compareAge(personsBirthDate: Calendar, today: Calendar): Boolean {

        if ((today.get(Calendar.YEAR) - personsBirthDate.get(Calendar.YEAR)) > 18) {
            return true
        } else {
            if ((today.get(Calendar.YEAR) - personsBirthDate.get(Calendar.YEAR)) == 18) {
                if ((today.get(Calendar.MONTH) - personsBirthDate.get(Calendar.MONTH)) > 0) {
                    return true
                } else {
                    if ((today.get(Calendar.MONTH) - personsBirthDate.get(Calendar.MONTH)) == 0) {
                        if ((today.get(Calendar.DAY_OF_MONTH) - personsBirthDate.get(Calendar.DAY_OF_MONTH)) >= 0) {
                            return true
                        }
                    }
                }
            }
        }
        return false
    }
}
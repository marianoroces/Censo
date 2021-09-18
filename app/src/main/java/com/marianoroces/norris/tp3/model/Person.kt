package com.marianoroces.norris.tp3.model

import java.io.Serializable

data class Person(
    val id: Int,
    val tipoDni: String,
    val dni: String,
    val nombre: String,
    val apellido: String,
    val fechaNacimiento: String,
    var sexo: String,
    var direccion: String,
    var telefono: String,
    var ocupacion: String,
    var ingresos: Int
) : Serializable {

}

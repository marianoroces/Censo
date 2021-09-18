package com.marianoroces.norris.tp3.testInterface

import com.marianoroces.norris.tp3.model.Person
import java.util.*

interface IDbHelperTest {
    fun savePerson(person: Person): Boolean
    fun getAll(): List<Person>
    fun validateAge(birthDate: Calendar, today: Calendar): Boolean
}
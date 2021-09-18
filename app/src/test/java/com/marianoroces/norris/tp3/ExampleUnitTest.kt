package com.marianoroces.norris.tp3


import com.marianoroces.norris.tp3.dao.DbHelper
import com.marianoroces.norris.tp3.model.Person
import com.marianoroces.norris.tp3.testInterface.IDbHelperTest
import org.junit.Assert

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val db: IDbHelperTest = Mockito.mock(IDbHelperTest::class.java)

    @Test
    fun testSavePerson() {
        val person: Person = Person(
            0,
            "DNI",
            "32568741",
            "juan",
            "perez",
            "3/11/85",
            "MASCULINO",
            "Cordoba 2478",
            "3425789632",
            "EMPLEADO",
            50000
        )
        Mockito.`when`(db.savePerson(person)).thenReturn(true)
        Assert.assertEquals(db.savePerson(person), true)
    }

    @Test
    fun testAge() {
        val today: Calendar = Calendar.getInstance()
        val birthDate: Calendar = Calendar.getInstance()
        val format: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        birthDate.time = format.parse("23/7/2003")
        Mockito.`when`(db.validateAge(birthDate, today)).thenReturn(true)

        Assert.assertEquals(db.validateAge(birthDate, today), true)
        Assert.assertEquals(db.validateAge(today, birthDate), false)
    }

    @Test
    fun testGetAll() {
        val resultado: List<Person> = ArrayList()
        val lista: List<Person> = ArrayList()
        Mockito.`when`(db.getAll()).thenReturn(lista)

        Assert.assertEquals(db.getAll(), resultado)
    }
}
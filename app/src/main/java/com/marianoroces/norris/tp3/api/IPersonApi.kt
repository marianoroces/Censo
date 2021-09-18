package com.marianoroces.norris.tp3.api


import com.marianoroces.norris.tp3.model.Person
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface IPersonApi {

    @GET(" PATH ")
    fun getAll(): Call<List<Person>>

    @GET(" PATH ")
    fun getPoorPeople(): Call<List<Person>>

    @GET(" PATH ")
    fun getUnemployedPeople(): Call<List<Person>>

    @PUT(" PATH ")
    fun updatePerson(): Call<Person>

    @POST(" PATH ")
    fun savePerson(): Call<Person>

    @DELETE(" PATH ")
    fun deletePerson(): Call<Person>

}
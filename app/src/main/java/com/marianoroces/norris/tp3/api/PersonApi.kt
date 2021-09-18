package com.marianoroces.norris.tp3.api

import com.marianoroces.norris.tp3.model.Person
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonApi {

    private fun getRetrofit() : Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(" >>> URL <<< ")
            .build()
    }

    fun getAll(): Call<List<Person>>{
        return getRetrofit().create(IPersonApi::class.java).getAll()
    }

    fun getPoorPeople(): Call<List<Person>>{
        return getRetrofit().create(IPersonApi::class.java).getPoorPeople()
    }

    fun getUnemployedPeople(): Call<List<Person>>{
        return getRetrofit().create(IPersonApi::class.java).getUnemployedPeople()
    }

    fun savePerson(): Call<Person>{
        return getRetrofit().create(IPersonApi::class.java).savePerson()
    }

    fun deletePerson(): Call<Person>{
        return getRetrofit().create(IPersonApi::class.java).deletePerson()
    }

    fun updatePerson(): Call<Person>{
        return getRetrofit().create(IPersonApi::class.java).updatePerson()
    }
}
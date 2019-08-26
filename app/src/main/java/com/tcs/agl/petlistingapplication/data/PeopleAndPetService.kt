package com.tcs.agl.petlistingapplication.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tcs.agl.petlistingapplication.data.model.People
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * This is an interface that helps in consuming the response from the service and return a list of [People]
 */
interface PeopleAndPetService {

    @GET("people.json")
    fun getPeopleList( ):Deferred<List<People>>

    companion object{
        operator fun invoke() : PeopleAndPetService{
            return Retrofit.Builder()
                    .baseUrl("http://agl-developer-test.azurewebsites.net/")
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PeopleAndPetService::class.java)
        }
    }
}

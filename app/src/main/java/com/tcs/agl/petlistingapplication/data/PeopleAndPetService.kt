package com.tcs.agl.petlistingapplication.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tcs.agl.petlistingapplication.data.model.People
import com.tcs.agl.petlistingapplication.data.network.ConnectivityInterceptor
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Helps in consuming the response from the service and return a list of [People]
 */
interface PeopleAndPetService {

    @GET("people.json")
    fun getPeopleList( ):Deferred<List<People>>

    companion object{
        /**
         * Fetches response from the JSON service
         * @property connectivityInterceptor is interface for intercepting connectivity related concerns
         */
        operator fun invoke(
                connectivityInterceptor: ConnectivityInterceptor
        ) : PeopleAndPetService{
            val okHttpClient= OkHttpClient.Builder()
                    .addInterceptor(connectivityInterceptor)
                    .build()

            return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://agl-developer-test.azurewebsites.net/")
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PeopleAndPetService::class.java)
        }
    }
}

package com.tcs.agl.petlistingapplication.data

import androidx.lifecycle.LiveData
import com.tcs.agl.petlistingapplication.data.model.People

/**
 * Fetches data from network or Database
 */
interface PeopleAndPetRepository {
    suspend fun getPeopleList(): LiveData<out List<People>>
}
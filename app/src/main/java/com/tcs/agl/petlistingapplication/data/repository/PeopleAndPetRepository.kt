package com.tcs.agl.petlistingapplication.data.repository

import androidx.lifecycle.LiveData
import com.tcs.agl.petlistingapplication.data.model.People

/**
 * Fetches data from network or Database
 */
interface PeopleAndPetRepository {
    suspend fun getFetchedPeopleList(): LiveData<out List<People>>
}
package com.tcs.agl.petlistingapplication.data.network

import androidx.lifecycle.LiveData
import com.tcs.agl.petlistingapplication.data.model.People

/**
 * For fetching data from network calls
 */
interface NetworkPeopleListDataSource {
    val fetchedPeopleList: LiveData<List<People>>

    /**
     * Fetches List of [People] object
     */
    suspend fun fetchPeopleList()
}
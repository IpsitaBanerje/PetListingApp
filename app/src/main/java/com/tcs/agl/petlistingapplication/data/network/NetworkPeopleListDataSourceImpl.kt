package com.tcs.agl.petlistingapplication.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tcs.agl.petlistingapplication.data.model.People
import com.tcs.agl.petlistingapplication.internal.ConnectivityUnavailableException
import java.lang.Exception

/**
 * Implements [NetworkPeopleListDataSource] to set value for [fetchedPeopleList] through [MutableLiveData] [_fetchedPeopleList]
 */
class NetworkPeopleListDataSourceImpl(
        private val peopleAndPetService: PeopleAndPetService
) : NetworkPeopleListDataSource {

    private val _fetchedPeopleList = MutableLiveData<List<People>>()

    override val fetchedPeopleList: LiveData<List<People>>
        get() = _fetchedPeopleList

    /**
     * Gets a [List] of [People] from [PeopleAndPetService.getPeopleListAsync] method and
     * posts the value on a [MutableLiveData] object
     */
    override suspend fun fetchPeopleList() {
        try {
            val peopleList = peopleAndPetService
                    .getPeopleListAsync()
                    .await()
            _fetchedPeopleList.postValue(peopleList)
        } catch (connUnavailableEx: ConnectivityUnavailableException) {
            Log.e("ConnectivityIssue", "No internet connection available.", connUnavailableEx)
        } catch (ex: Exception) {
            Log.e("ConnectivityIssue", "Exception encountered", ex)
        }
    }
}
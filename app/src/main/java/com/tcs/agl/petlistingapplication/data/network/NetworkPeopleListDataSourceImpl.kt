package com.tcs.agl.petlistingapplication.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tcs.agl.petlistingapplication.data.PeopleAndPetService
import com.tcs.agl.petlistingapplication.data.model.People
import com.tcs.agl.petlistingapplication.internal.ConnectivityUnavailableException

class NetworkPeopleListDataSourceImpl(
        private val peopleAndPetService: PeopleAndPetService
) : NetworkPeopleListDataSource {

    private val _fetchedPeopleList = MutableLiveData<List<People>>()

    override val fetchedPeopleList: LiveData<List<People>>
        get() = _fetchedPeopleList

    override suspend fun fetchPeopleList() {
        try{
            val peopleList = peopleAndPetService
                    .getPeopleListAsync()
                    .await()
            _fetchedPeopleList.postValue(peopleList)
        }
        catch (connUnavailableEx: ConnectivityUnavailableException){
            Log.e("ConnectivityIssue", "No internet connection available.", connUnavailableEx)
        }
    }
}
package com.tcs.agl.petlistingapplication.data

import androidx.lifecycle.LiveData
import com.tcs.agl.petlistingapplication.data.model.People
import com.tcs.agl.petlistingapplication.data.model.Pet
import com.tcs.agl.petlistingapplication.data.network.NetworkPeopleListDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  Implementation of [PeopleAndPetRepository] to perform operations on [People] and [Pet] data
 *  It can be used to perform operation to/ from network or database
 *  @property networkPeopleListDataSource the object that provides [People] list data
 */
class PeopleAndPetRepositoryImpl(
        private val networkPeopleListDataSource: NetworkPeopleListDataSource
) : PeopleAndPetRepository {

    /**
     * Gets the list of [People] object
     * @return [LiveData] of [List] of [People] object
     */
    override suspend fun getFetchedPeopleList():LiveData<out List<People>> {
        return withContext(Dispatchers.IO) {
            networkPeopleListDataSource.fetchPeopleList()
            return@withContext networkPeopleListDataSource.fetchedPeopleList
        }
    }
}
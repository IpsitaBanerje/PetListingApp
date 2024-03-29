package com.tcs.agl.petlistingapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.tcs.agl.petlistingapplication.data.repository.PeopleAndPetRepository
import com.tcs.agl.petlistingapplication.data.model.People
import com.tcs.agl.petlistingapplication.internal.lazyAndDeferred

/**
 * Used to provide data to view
 */
class PeopleViewModel(
        private val peopleAndPetRepository: PeopleAndPetRepository
) : ViewModel() {

    /**
     * Gets list of [People] object
     */
    val peopleList by lazyAndDeferred {
        peopleAndPetRepository.getFetchedPeopleList()
    }
}
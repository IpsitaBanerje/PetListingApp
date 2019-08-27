package com.tcs.agl.petlistingapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tcs.agl.petlistingapplication.data.repository.PeopleAndPetRepository

/**
 * Used to hold data and state for [PeopleViewModel]
 */
class PeopleViewModelFactory(
        private val peopleAndPetRepository: PeopleAndPetRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PeopleViewModel(peopleAndPetRepository) as T
    }
}
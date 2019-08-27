package com.tcs.agl.petlistingapplication.data.model

/**
 * This class is the data model representing [People]
 */
data class People(
        val name: String,
        val gender: String,
        val age: Int,

        /**
         * List of [Pet] owned by the [People] object
         */
        val pets: List<Pet> = ArrayList()
)
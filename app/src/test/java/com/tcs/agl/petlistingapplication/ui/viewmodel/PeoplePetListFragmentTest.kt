package com.tcs.agl.petlistingapplication.ui.viewmodel

import com.tcs.agl.petlistingapplication.data.model.People
import com.tcs.agl.petlistingapplication.data.model.Pet
import com.tcs.agl.petlistingapplication.ui.PeoplePetListFragment
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Tests [PeoplePetListFragment] for different input parameters
 */
class PeoplePetListFragmentTest {

     private lateinit var peoplePetListFragment: PeoplePetListFragment

    @Before
    fun setUp() {
        peoplePetListFragment = PeoplePetListFragment()
    }


    @Test
    fun getGenderToCatListHashMap_shouldHandleEmptyPetList() {
        // create a dummy list of person that is to be tested
        val peopleFirst= People("Jack","Male",21, listOf())
        val peopleSecond= People("Jim","Female",22, listOf())
        val peopleList= listOf(peopleFirst,peopleSecond)
        // call the method and store the response of the method in the form of hasp
        val genderToCatListMap= peoplePetListFragment.getGenderToCatListHashMap(peopleList)
        // assert the result of hash map
        Assert.assertTrue(genderToCatListMap.size == 0)
    }




    @Test
    fun getGenderToCatListHashMap_shouldHandleEmptyPersonList() {
        val peopleList= listOf<People>()
        // call the method and store the response of the method in the form of hasp
        val genderToCatListMap= peoplePetListFragment.getGenderToCatListHashMap(peopleList)
        // assert the result of hash map
        Assert.assertTrue(genderToCatListMap.size == 0)
    }

    @Test
    fun getGenderToCatListHashMap_positiveWithNonEmptyPetListForMale() {
        // create a dummy list of person that you want to test
        val pet1= Pet("Gary", "Cat")
        val pet2= Pet("Tommy", "Dog")
        val peopleFirst= People("Jack","Male",21, listOf(pet1,pet2))
        val petSecond1= Pet("Gary", "Fish")
        val petSecond2= Pet("Tommy", "Dog")
        val peopleSecond= People("Jimmy","Male",21, listOf(petSecond1, petSecond2))
        val peopleList= listOf(peopleFirst,peopleSecond)
        // call the method and store the response of the method in the form of hasp
        val genderToCatListMap= peoplePetListFragment.getGenderToCatListHashMap(peopleList)
        // assert the result of hash map
        Assert.assertTrue(genderToCatListMap.size == 1)
        Assert.assertTrue(genderToCatListMap.containsKey("Male"))
        Assert.assertFalse(genderToCatListMap.containsKey("Female"))
        Assert.assertTrue(genderToCatListMap["Male"]?.size == 1)
        Assert.assertEquals("Gary",genderToCatListMap["Male"]?.get(0))
    }

    @Test
    fun getGenderToCatListHashMap_WithEmptyAndNonEmptyPetList_CatForMultipleGender_positive() {
        // create a dummy list of person that you want to test
        val petFirst1= Pet("Gary", "Cat")
        val petFirst2= Pet("Tommy", "Dog")
        val peopleFirst= People("Jack","Male",21, listOf(petFirst1,petFirst2))
        val petSecond1= Pet("Garry", "Fish")
        val petSecond2= Pet("Tommy", "Cat")
        val peopleSecond= People("Jimmy","Female",21, listOf(petSecond1, petSecond2))
        val peopleThird= People("Timmy","Male",21, listOf())
        val petForth1= Pet("Sally", "Fish")
        val petForth2= Pet("Maxie", "Cat")
        val peopleForth= People("Lilly","Female",21, listOf(petForth1, petForth2))
        val peopleList= listOf(peopleFirst,peopleSecond,peopleThird, peopleForth)
        // call the method and store the response of the method in the form of hasp
        val genderToCatListMap= peoplePetListFragment.getGenderToCatListHashMap(peopleList)
        // assert the result of hash map
        Assert.assertTrue(genderToCatListMap.size == 2)
        Assert.assertTrue(genderToCatListMap.containsKey("Male"))
        Assert.assertTrue(genderToCatListMap.containsKey("Female"))
        Assert.assertTrue(genderToCatListMap["Male"]?.size == 1)
        Assert.assertTrue(genderToCatListMap["Female"]?.size == 2)
        Assert.assertEquals("Gary",genderToCatListMap["Male"]?.get(0))
    }

    @Test
    fun getGenderToCatListHashMap_WithEmptyAndNonEmptyPetList_CatForSingleGender_positive() {
        // create a dummy list of person that you want to test
        val petFirst1= Pet("Gary", "Cat")
        val petFirst2= Pet("Tommy", "Dog")
        val peopleFirst= People("Jack","Male",21, listOf(petFirst1,petFirst2))
        val petSecond1= Pet("Gary", "Fish")
        val petSecond2= Pet("Tommy", "Dog")
        val peopleSecond= People("Jimmy","Female",21, listOf(petSecond1, petSecond2))
        val peopleThird= People("Timmy","Male",21, listOf())
        // call the method and store the response of the method in the form of hasp
        val genderToCatListMap= peoplePetListFragment.getGenderToCatListHashMap(listOf(peopleFirst,peopleSecond,peopleThird))
        // assert the result of hash map
        Assert.assertTrue(genderToCatListMap.size == 1)
        Assert.assertTrue(genderToCatListMap.containsKey("Male"))
        Assert.assertFalse(genderToCatListMap.containsKey("Female"))
        Assert.assertTrue(genderToCatListMap["Male"]?.size == 1)
        Assert.assertEquals("Gary",genderToCatListMap["Male"]?.get(0))
    }

}
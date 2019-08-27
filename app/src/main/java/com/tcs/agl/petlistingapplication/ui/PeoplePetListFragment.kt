package com.tcs.agl.petlistingapplication.ui

import com.tcs.agl.petlistingapplication.R
import kotlinx.android.synthetic.main.people_pet_list_fragment.*
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.lifecycle.Observer
import com.tcs.agl.petlistingapplication.data.model.People
import com.tcs.agl.petlistingapplication.data.model.Pet
import com.tcs.agl.petlistingapplication.ui.viewmodel.PeopleViewModel
import com.tcs.agl.petlistingapplication.ui.viewmodel.PeopleViewModelFactory
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

private const val NEW_LINE= "\n"

/**
 * Inherits [ScopedLaunchFragment] and implements [KodeinAware]
 * Binds data with UI objects
 */
class PeoplePetListFragment : ScopedLaunchFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: PeopleViewModelFactory by instance()
    private lateinit var viewModel: PeopleViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.people_pet_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(PeopleViewModel::class.java)
        bindUI()
    }

    /**
     * Observes for change in [PeopleViewModel.peopleList]
     * On receiving the [List] of [People] object traverses and populates in TextView
     */
    private fun bindUI() = launch {
        val peopleListData = viewModel.peopleList.await()

        peopleListData.observe(this@PeoplePetListFragment, Observer {
            if (it == null) return@Observer

            group_loading.visibility = View.GONE

            generateOutputAndUpdateUI(it)
        })
    }

    /**
     * Gets formatted output for UI and sets it to TextView
     * @property [List] of [People]
     */
    private fun generateOutputAndUpdateUI(peopleList: List<People>) {
        val result =getFormattedOutputFromGenderToCatListHashMap(getGenderToCatListHashMap(peopleList))
        if (peopleList.isNullOrEmpty() || result.isEmpty()){
            textView_pet_list.text= getString(R.string.data_unavailable)
        }
        else
            textView_pet_list.text = result
    }

    /**
     * Creates [HashMap] of [String] keys for [People.gender] and [ArrayList]of [String] as value for [Pet.name]
     * @property [List] of [People] obtained
     */
    fun getGenderToCatListHashMap(peopleList: List<People>): java.util.HashMap<String, java.util.ArrayList<String>> {
        val genderToPetHashMap: HashMap<String, ArrayList<String>> = HashMap()

        for (people in peopleList){
            if (!people.pets.isNullOrEmpty()){
                val catList= filterCats(people.pets)
                if (!catList.isNullOrEmpty()){
                    if(genderToPetHashMap.containsKey(people.gender)){
                        genderToPetHashMap[people.gender]?.addAll(catList)
                    }else{
                        genderToPetHashMap[people.gender] = catList
                    }
                }
            }
        }
        return genderToPetHashMap
    }

    /**
     * Gives output in required format
     * @property [HashMap] of [String] and [ArrayList]
     * @return [SpannableStringBuilder]
     */
    private fun getFormattedOutputFromGenderToCatListHashMap(genderToPetHashMap: java.util.HashMap<String, java.util.ArrayList<String>>): SpannableStringBuilder {
        val output = SpannableStringBuilder("")

        for (genderToPetObj in genderToPetHashMap){
            output.bold { append(genderToPetObj.key+ NEW_LINE) }
            for (catName in genderToPetObj.value.sorted()){
                output.append(wrapInBullet(catName))
            }
            output.append(NEW_LINE)
        }
        return output
    }

    private fun wrapInBullet(key: String): String {
        return "\u2022 $key$NEW_LINE"
    }

    private fun filterCats(pets: List<Pet>): ArrayList<String> {
        val catList:ArrayList<String> = ArrayList()
        for (pet in pets){
            if ("Cat".equals(pet.type, true)){
                catList.add(pet.name)
            }
        }
        return catList
    }
}

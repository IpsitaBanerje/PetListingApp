package com.tcs.agl.petlistingapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.tcs.agl.petlistingapplication.data.PeopleAndPetService
import com.tcs.agl.petlistingapplication.ui.PeopleViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * This is an activity that helps in filling up data in the view from the view model
 */
class PetListActivity : AppCompatActivity() {

    private lateinit var peopleViewModel: PeopleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        peopleViewModel = ViewModelProviders.of(this).get(PeopleViewModel::class.java)
        //TODO: Use the ViewModel
        val jsonService = PeopleAndPetService()

        GlobalScope.launch(Dispatchers.Main) {
            val peopleJsonResponse = jsonService.getPeopleList().await()
            textView.text = peopleJsonResponse.toString()
        }
    }
}

package com.tcs.agl.petlistingapplication

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tcs.agl.petlistingapplication.ui.PeopleViewModel
import com.tcs.agl.petlistingapplication.ui.PeopleViewModelFactory
import com.tcs.agl.petlistingapplication.ui.ScopedActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

/**
 * Fills up data obtained in the view
 */
class PetListActivity : ScopedActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory:PeopleViewModelFactory by instance()
    private lateinit var peopleViewModel: PeopleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        peopleViewModel = ViewModelProviders.of(this, viewModelFactory).get(PeopleViewModel::class.java)
        bindUI()
    }

    /**
     * Binds data with UI
     */
    private fun bindUI() = launch{
        val peopleListData = peopleViewModel.peopleList.await()
        peopleListData.observe(this@PetListActivity, Observer {
            if (it == null) return@Observer

            textView.text = it.toString()
        })
    }
}

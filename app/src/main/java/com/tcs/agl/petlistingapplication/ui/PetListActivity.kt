package com.tcs.agl.petlistingapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tcs.agl.petlistingapplication.R
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

/**
 * Fills up data obtained in the view
 */
class PetListActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

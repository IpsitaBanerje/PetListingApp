package com.tcs.agl.petlistingapplication

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.tcs.agl.petlistingapplication.data.PeopleAndPetRepository
import com.tcs.agl.petlistingapplication.data.PeopleAndPetRepositoryImpl
import com.tcs.agl.petlistingapplication.data.PeopleAndPetService
import com.tcs.agl.petlistingapplication.data.network.ConnectivityInterceptor
import com.tcs.agl.petlistingapplication.data.network.ConnectivityInterceptorImpl
import com.tcs.agl.petlistingapplication.data.network.NetworkPeopleListDataSource
import com.tcs.agl.petlistingapplication.data.network.NetworkPeopleListDataSourceImpl
import com.tcs.agl.petlistingapplication.ui.PeopleViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Used for Dependency Injection
 */
class PeopleAndPetApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy{
        import(androidXModule(this@PeopleAndPetApplication))

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance())}
        bind() from singleton { PeopleAndPetService(instance()) }
        bind<NetworkPeopleListDataSource>() with singleton { NetworkPeopleListDataSourceImpl(instance())}
        bind<PeopleAndPetRepository>() with singleton { PeopleAndPetRepositoryImpl(instance())}
        bind() from provider { PeopleViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

}
package com.tcs.agl.petlistingapplication.data.network

import okhttp3.Interceptor
import  com.tcs.agl.petlistingapplication.data.PeopleAndPetService

/**
 *
 * This is an interceptor for connectivity that is passed in [PeopleAndPetService]
 * for implementing dependency injection
 * This interface is implemented by [ConnectivityInterceptorImpl]
 */
interface ConnectivityInterceptor :Interceptor
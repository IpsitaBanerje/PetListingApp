package com.tcs.agl.petlistingapplication.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.tcs.agl.petlistingapplication.internal.ConnectivityUnavailableException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * This is an implementation that takes care of internet unavailability during service call by Retrofit
 */

class ConnectivityInterceptorImpl(
        context: Context
) : ConnectivityInterceptor {

    private val applicationContext = context.applicationContext

    /**
     * Implements [intercept] to check for connectivity
     * @throws [ConnectivityUnavailableException] if Internet connectivity is unavailable
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable())
            throw ConnectivityUnavailableException()
        return chain.proceed(chain.request())
    }

    /**
     * Checks if network is available and returns [Boolean] after checking networkInfo
     * @return [Boolean] true if available and false if not
     */
    private fun isNetworkAvailable(): Boolean {
        val connectivityServiceManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityServiceManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
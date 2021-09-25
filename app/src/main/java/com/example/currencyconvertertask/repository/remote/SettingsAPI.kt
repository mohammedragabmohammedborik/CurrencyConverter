package com.example.currencyconvertertask.repository.remote

import com.example.currencyconvertertask.BuildConfig
import javax.inject.Inject

/**
 * Sample class that has the Api Key, Base URL and Timeout for Retrofit.
 */

class SettingsAPI
@Inject
constructor() {
    val access_key: String
        get() = BuildConfig.API_KEY

    val baseUrl: String
        get() = BuildConfig.BASE_URL

    val timeout: Long
        get() = 30000
}
package com.example.currencyconvertertask.datalayer.data

import com.example.currencyconvertertask.repository.NetworkState

data class CurrencyRateDataOffline(
        val networkState: NetworkState?=null,
        val map: MutableMap<String, Double>,
        val base:String

)
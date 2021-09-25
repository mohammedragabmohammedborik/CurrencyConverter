package com.example.currencyconvertertask.datalayer.data

import com.example.currencyconvertertask.repository.NetworkState

data class CurrencyRateData(
        val networkState: NetworkState,
        val hashMap: MutableMap<String,Double>? = null,
        val base:String?=null

)
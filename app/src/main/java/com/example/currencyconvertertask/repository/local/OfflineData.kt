package com.example.currencyconvertertask.repository.local

import com.example.currencyconvertertask.datalayer.data.CurrencyRateDataOffline


object OfflineData {

  suspend fun getCurrencyRate():CurrencyRateDataOffline{
     val map= mutableMapOf<String,Double>()
     map.put("AED",2.2)
     map.put("USD",1.1)
     map.put("AUD",1.6)
     map.put("CAD",1.234)
     map.put("PLN",.2)
     map.put("AOA",3.25)
     map.put("BBD",34.29)
     map.put("BOB",11.2)
     map.put("BRL",345.2989)
     map.put("ALL",2.245)
     map.put("BTN",3.76)
     map.put("CLF",8.99)
     val base="EUR"
     return CurrencyRateDataOffline(null,map,base)

 }

}
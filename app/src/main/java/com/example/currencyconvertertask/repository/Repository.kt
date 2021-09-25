package com.teraninjas.mazadat.repository

import com.example.currencyconvertertask.datalayer.BaseResponse
import retrofit2.Response
import retrofit2.http.Query

interface Repository {
 suspend   fun getCurrencyRates(): Response<BaseResponse?>

}
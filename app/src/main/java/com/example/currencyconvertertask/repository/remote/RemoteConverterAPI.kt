package com.example.currencyconvertertask.repository.remote

import com.example.currencyconvertertask.datalayer.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * API Endpoints
 */

interface RemoteConverterAPI {

    @Headers("accept: */*")
    @GET("api/latest")
  suspend  fun getCurrencyRates(): Response<BaseResponse?>

}
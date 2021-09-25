package com.example.currencyconvertertask.repository
import com.example.currencyconvertertask.datalayer.BaseResponse
import com.example.currencyconvertertask.repository.remote.RemoteConverterAPI
import com.teraninjas.mazadat.repository.Repository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val remoteDataSource: RemoteConverterAPI,
) : Repository {
     override  suspend fun getCurrencyRates():Response<BaseResponse?> {
       return  remoteDataSource.getCurrencyRates()
    }
}

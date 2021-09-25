package com.example.currencyconvertertask.di
import com.example.currencyconvertertask.repository.remote.EmbedAPIKeyInterceptor
import com.example.currencyconvertertask.repository.remote.RemoteConverterAPI
import com.example.currencyconvertertask.repository.remote.SettingsAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// @Module informs Dagger that this class is a Dagger Module
@Module
class NetworkModule {
    // @Provides tell Dagger how to create instances of the type that this function
    // Function parameters are the dependencies of this type.

    @Provides
    internal fun provideHttpClient(settings: SettingsAPI, keyInterceptor: EmbedAPIKeyInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(settings.timeout, TimeUnit.MILLISECONDS)
        httpClient.writeTimeout(settings.timeout, TimeUnit.MILLISECONDS)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor(keyInterceptor)

        return httpClient.build()
    }

    @Provides
    internal fun provideApiKeyInterceptor(settings: SettingsAPI) =
        EmbedAPIKeyInterceptor(settings)

    @Singleton
    @Provides
    fun provideLoginRetrofitService(httpClient: OkHttpClient, settings: SettingsAPI
    ):RemoteConverterAPI {

        // Whenever Dagger needs to provide an instance of type RemoteConverterAPI,
        // this code (the one inside the @Provides method) is run.
        return Retrofit.Builder()
            .baseUrl(settings.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(RemoteConverterAPI::class.java)

    }


}
package com.example.currencyconvertertask.di
import com.example.currencyconvertertask.repository.RepositoryImpl
import com.teraninjas.mazadat.repository.Repository
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryModule.BinderRepositiory::class])
class RepositoryModule {
     @Module
     interface  BinderRepositiory
     {
          @Binds
          fun repositoryBind(authRepositoryImpl: RepositoryImpl): Repository

     }

}
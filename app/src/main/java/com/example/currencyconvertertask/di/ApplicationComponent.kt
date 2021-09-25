package com.example.currencyconvertertask.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// The "modules" attribute in the @Component annotation tells Dagger what Modules
// to include when building the graph
@Singleton
@Component(modules = [ModuleAppSubcomponents::class,NetworkModule::class, RepositoryModule::class
    ,FactoryModule::class])

  interface ApplicationComponent {
    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
    // Expose homeComponent factory from the graph

    fun homeComponent():HomeComponent.Factory









}
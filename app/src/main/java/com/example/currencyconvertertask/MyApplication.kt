package com.example.currencyconvertertask

import android.app.Application
import com.example.currencyconvertertask.di.ApplicationComponent
import com.example.currencyconvertertask.di.DaggerApplicationComponent

class MyApplication:Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}
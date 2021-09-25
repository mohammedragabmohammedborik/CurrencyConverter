package com.example.currencyconvertertask.di

import com.example.currencyconvertertask.databinding.LayoutCalculationBinding
import com.example.currencyconvertertask.ui.MainActivity
import com.example.currencyconvertertask.ui.fragment.HomeFragment
import com.example.currencyconvertertask.ui.fragment.OnlineFragment
import com.example.currencyconvertertask.ui.fragment.calculation.CalculationFragment
import dagger.Subcomponent

@Subcomponent
interface HomeComponent {
    // Factory to create instances of AuthComponent

    @Subcomponent.Factory
     interface Factory{
        fun  create():HomeComponent
    }

    fun  inject(mainActivity: MainActivity)
    fun  inject(homeFragment: HomeFragment)
    fun  inject(onlineFragment: OnlineFragment)
    fun  inject(calculationFragment: CalculationFragment)






























}

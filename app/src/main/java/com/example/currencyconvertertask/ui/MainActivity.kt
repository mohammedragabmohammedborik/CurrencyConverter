package com.example.currencyconvertertask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.currencyconvertertask.MyApplication
import com.example.currencyconvertertask.R
import com.example.currencyconvertertask.databinding.ActivityMainBinding
import com.example.currencyconvertertask.di.HomeComponent
import com.example.currencyconvertertask.presentation.ShareDataBetweenFragmentViewModel

class MainActivity : AppCompatActivity() {
    lateinit var  homeComponent: HomeComponent
    private   lateinit var  binding: ActivityMainBinding
    private lateinit var  navController : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val shareDataBetweenFragmentViewModel: ShareDataBetweenFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        homeComponent=    (application as MyApplication).applicationComponent.homeComponent().create()
        homeComponent.inject(this)
        super.onCreate(savedInstanceState)
       binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        navController = findNavController(R.id.nav_host_fragment)
        //

        appBarConfiguration = AppBarConfiguration(setOf(R.id.onlineFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.bottomNavigation.setupWithNavController(navController)

        /**
         * add navigation listener to hide tool bar in fragment calculation
         */
        navController.addOnDestinationChangedListener(NavController.OnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.calculationFragment->{

                    binding.toolbar.visibility= View.GONE
                    binding.bottomNavigation.visibility=View.GONE

                }
                R.id.homeFragment->{

                    binding.toolbar.visibility= View.VISIBLE
                    binding.bottomNavigation.visibility=View.VISIBLE


                }
                R.id.onlineFragment->{

                    binding.toolbar.visibility= View.VISIBLE
                    binding.bottomNavigation.visibility=View.VISIBLE


                }




            }
        })

        /**
         * observe for name baseCurrence
         *
         */
        shareDataBetweenFragmentViewModel.baseName.observe(this, Observer { baseName->
            baseName?.let { binding.currencyName.text=it }
        })


    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
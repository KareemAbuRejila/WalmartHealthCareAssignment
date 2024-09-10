package com.dotech.walmarthealthcareassignment.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dotech.walmarthealthcareassignment.R
import com.dotech.walmarthealthcareassignment.presentation.network.NetworkConnectionMonitor
import com.dotech.walmarthealthcareassignment.presentation.ui.countries.CountriesFragment
import com.dotech.walmarthealthcareassignment.databinding.ActivityHomeBinding


class MainActivity : AppCompatActivity(){

    private lateinit var networkMonitor: NetworkConnectionMonitor
    private val marinViewModel by viewModels<MarinViewModel>()
    private lateinit var  binding: ActivityHomeBinding
    private lateinit var fm : FragmentManager
    private val listFragments = listOf(
        CountriesFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setupBottomNavigationBar()

    }

    private fun setupBottomNavigationBar() {
        setSupportActionBar(binding.toolbar)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_setting, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController,appBarConfiguration)
        binding.navigation.setupWithNavController(navController)
        supportActionBar?.title = "Test"
    }



    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        networkMonitor = NetworkConnectionMonitor(this)
    }



    override fun onStart() {
        super.onStart()
    }

    private fun networkMonitor() {
        networkMonitor.observe(this) { isConnected ->
            if (isConnected) {
                // Refresh the country list when the internet is available

            } else {
//                binding.msg = "No internet connection"
            }
        }
    }
}

package com.dotech.walmarthealthcareassignment.app.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.dotech.walmarthealthcareassignment.R
import com.dotech.walmarthealthcareassignment.app.network.NetworkConnectionMonitor
import com.dotech.walmarthealthcareassignment.app.ui.adapters.CountriesListAdapter
import com.dotech.walmarthealthcareassignment.databinding.ActivityHomeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity(){

    private lateinit var networkMonitor: NetworkConnectionMonitor
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var  binding: ActivityHomeBinding
    private lateinit var countriesAdapter: CountriesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        setSwipeListener()
//        networkMonitor()

    }

    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        networkMonitor = NetworkConnectionMonitor(this)
        countriesAdapter = CountriesListAdapter {
            Snackbar.make(binding.root, it.name, Snackbar.LENGTH_SHORT).show()
        }
        binding.countriesAdapter = countriesAdapter
    }

    private fun setSwipeListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            homeViewModel.fetchAllCountries()
        }

    }

    override fun onStart() {
        super.onStart()
        homeViewModel.countries.observe(this) {
            countriesAdapter.submitList(it)
        }
        homeViewModel.isLoading.observe(this) {
            binding.loading=it
            binding.swipeRefreshLayout.isRefreshing = it
        }
        homeViewModel.error.observe(this) {
            binding.msg = it
            if (it.isNotBlank())Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
        homeViewModel.networkStatusLiveData.observe(this){
            if (it) homeViewModel.fetchAllCountries()
        }

    }

    private fun networkMonitor() {
        networkMonitor.observe(this) { isConnected ->
            if (isConnected) {
                // Refresh the country list when the internet is available
                homeViewModel.fetchAllCountries()
            } else {
                binding.msg = "No internet connection"
            }
        }
    }
}

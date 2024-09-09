package com.dotech.walmarthealthcareassignment.app.ui.countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.dotech.walmarthealthcareassignment.app.MyApp
import com.dotech.walmarthealthcareassignment.app.common.ViewModelFactory
import com.dotech.walmarthealthcareassignment.app.ui.adapters.CountriesListAdapter
import com.dotech.walmarthealthcareassignment.databinding.FragmentCountriesBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class CountriesFragment : Fragment() {

    private var _binding: FragmentCountriesBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CountriesViewModel>

    private val viewModel: CountriesViewModel by lazy {
        viewModelFactory.get<CountriesViewModel>(
            requireActivity()
        )
    }


    private lateinit var _countriesAdapter: CountriesListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        _countriesAdapter = CountriesListAdapter {
            Snackbar.make(binding.root, it.name, Snackbar.LENGTH_SHORT).show()
        }
        binding.countriesAdapter = _countriesAdapter
        setSwipeListener()
    }

    private fun setSwipeListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchAllCountries()
        }

    }

    override fun onStart() {
        super.onStart()
        viewModel.countries.observe(this) {
            _countriesAdapter.submitList(it)
        }
        viewModel.isLoading.observe(this) {
            binding.swipeRefreshLayout.isRefreshing = it
        }
        viewModel.error.observe(this) {
            binding.msg = it
            if (it.isNotBlank()) Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
//        viewModel.networkStatusLiveData.observe(this) {
//            if (it) viewModel.fetchAllCountries()
//        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
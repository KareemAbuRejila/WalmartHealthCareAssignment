package com.dotech.walmarthealthcareassignment.app.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dotech.walmarthealthcareassignment.app.network.NetworkConnectionMonitor
import com.dotech.walmarthealthcareassignment.data.models.RemoteResponse
import com.dotech.walmarthealthcareassignment.domain.models.ComponentListState
import com.dotech.walmarthealthcareassignment.domain.models.Country
import com.dotech.walmarthealthcareassignment.domain.repositories.CountriesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val countriesRepo: CountriesRepo,
    @ApplicationContext private val context: Context
): ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries : LiveData<List<Country>> = _countries

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error
    private val _networkStatusLiveData = NetworkConnectionMonitor(context)
    val networkStatusLiveData = _networkStatusLiveData


    init {
        // Fetch countries only if they haven't been loaded yet
        if (_countries.value == null) {
            fetchAllCountries()
        }
    }

    fun fetchAllCountries(){
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            when(val response = countriesRepo.getAllCountries(context)){
                is RemoteResponse.Success -> {
                    _countries.postValue(response.data)
                    _isLoading.postValue(false)
                    _error.postValue("")
                }
                is RemoteResponse.Loading -> {
                    _isLoading.postValue(true)
                    _error.postValue("")
                }
                is RemoteResponse.Error -> {
                    _error.postValue(response.message)
                    _isLoading.postValue(false)
                }
            }
        }
    }

}
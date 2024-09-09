package com.dotech.walmarthealthcareassignment.data.repositories

import android.content.Context
import com.dotech.walmarthealthcareassignment.data.Utils
import com.dotech.walmarthealthcareassignment.data.local.repositories.CountriesLocalRepo
import com.dotech.walmarthealthcareassignment.data.models.RemoteResponse
import com.dotech.walmarthealthcareassignment.data.remote.apis.CountriesApi
import com.dotech.walmarthealthcareassignment.domain.models.Country
import com.dotech.walmarthealthcareassignment.domain.repositories.CountriesRepo
import java.net.SocketTimeoutException
import javax.inject.Inject

class CountriesRepoImpl @Inject constructor(
    private val api: CountriesApi,
    private val countriesLocalRepo: CountriesLocalRepo,
    private val context:Context
) : CountriesRepo {

    override suspend fun getAllCountries(): RemoteResponse<List<Country>?> {
            try {
                // Check if countries are available in the local database
                countriesLocalRepo.getAll().also {
//                    if (it.value!=null && it.value!!.isNotEmpty())
                    return if ( it.isNotEmpty())
                        RemoteResponse.Success(it)
                    else
                        return getFromRemote(context)
                }
            } catch (e: Exception) {
                return RemoteResponse.Error(e.message ?: "Exception")
            } catch (e: SocketTimeoutException) {
                return RemoteResponse.Error(e.message ?: "SocketTimeoutException")
            }

    }

    private suspend fun getFromRemote(context: Context): RemoteResponse<List<Country>> {
        if (Utils.isNetworkAvailable(context = context)) {
            // Fetch countries from API
            val response = api.getCountries().map { it.toCountry() }
            // Cache the countries in the database
            countriesLocalRepo.insertAll(response)
            return RemoteResponse.Success(response)
        } else {
            return RemoteResponse.Error("Check Internet")
        }
    }

}
package com.dotech.walmarthealthcareassignment.domain.repositories

import android.content.Context
import com.dotech.walmarthealthcareassignment.data.models.RemoteResponse
import com.dotech.walmarthealthcareassignment.domain.models.Country

interface CountriesRepo {

    suspend fun getAllCountries(context: Context): RemoteResponse<List<Country>?>
}
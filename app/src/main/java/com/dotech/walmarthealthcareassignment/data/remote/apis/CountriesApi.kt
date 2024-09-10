package com.dotech.walmarthealthcareassignment.data.remote.apis

import com.dotech.walmarthealthcareassignment.data.models.CountryDto
import com.dotech.walmarthealthcareassignment.data.remote.Constrains
import com.dotech.walmarthealthcareassignment.data.remote.Constrains.COUNTRIES
import com.dotech.walmarthealthcareassignment.data.remote.Constrains.PAYMANO_WMT
import com.dotech.walmarthealthcareassignment.data.remote.Constrains.RAW
import com.dotech.walmarthealthcareassignment.data.remote.Constrains.TOKEN
import com.dotech.walmarthealthcareassignment.domain.models.Country
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApi {

    @GET("$PAYMANO_WMT/{token}/" +
            "$RAW/db25946fd77c5873b0303b858e861ce724e0dcd0/" +
            "$COUNTRIES.json")
    suspend fun getCountries(@Path("token") token:String): List<CountryDto>
}
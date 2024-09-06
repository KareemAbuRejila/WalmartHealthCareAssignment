package com.dotech.walmarthealthcareassignment.data.local.repositories

import androidx.lifecycle.LiveData
import com.dotech.walmarthealthcareassignment.data.local.dao.CountryDao
import com.dotech.walmarthealthcareassignment.domain.models.Country
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesLocalRepo @Inject constructor(private val countryDao: CountryDao) {

    fun getAll(): List<Country> = countryDao.getAll()

    suspend fun insert(country: Country) = countryDao.insert(country)
    suspend fun insertAll(list: List<Country>) = countryDao.insertAll(list)
    suspend fun update(country: Country) = countryDao.update(country)
    suspend fun delete(country: Country) = countryDao.delete(country)
}
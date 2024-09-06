package com.dotech.walmarthealthcareassignment.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dotech.walmarthealthcareassignment.domain.models.Country

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: Country)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Country>)

    @Update
    suspend fun update(country: Country)

    @Delete
    suspend fun delete(country: Country)

    @Query("SELECT * FROM country_table")
    fun getAll(): List<Country>

    @Query("SELECT * FROM country_table WHERE code LIKE :countryCode")
    fun getOne(countryCode: String): Country

}
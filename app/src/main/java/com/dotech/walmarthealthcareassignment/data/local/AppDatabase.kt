package com.dotech.walmarthealthcareassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dotech.walmarthealthcareassignment.data.local.dao.CountryDao
import com.dotech.walmarthealthcareassignment.domain.models.Country

@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun countryDao(): CountryDao
}
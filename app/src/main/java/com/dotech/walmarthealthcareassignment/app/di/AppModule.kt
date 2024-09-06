package com.dotech.walmarthealthcareassignment.app.di

import android.content.Context
import androidx.room.Room
import com.dotech.walmarthealthcareassignment.BuildConfig
import com.dotech.walmarthealthcareassignment.data.local.AppDatabase
import com.dotech.walmarthealthcareassignment.data.local.dao.CountryDao
import com.dotech.walmarthealthcareassignment.data.local.repositories.CountriesLocalRepo
import com.dotech.walmarthealthcareassignment.data.remote.apis.CountriesApi
import com.dotech.walmarthealthcareassignment.data.repositories.CountriesRepoImpl
import com.dotech.walmarthealthcareassignment.domain.repositories.CountriesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService() = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountriesApi::class.java)

    @Provides
    @Singleton
    fun provideCountriesRepo(api: CountriesApi,countriesLocalRepo: CountriesLocalRepo):CountriesRepo {
        return CountriesRepoImpl(api,countriesLocalRepo)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase::class.java.simpleName
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideQuotesDao(database: AppDatabase): CountryDao = database.countryDao()

}
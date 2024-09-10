package com.dotech.walmarthealthcareassignment.presentation.di

import android.content.Context
import androidx.room.Room
import com.dotech.walmarthealthcareassignment.BuildConfig
import com.dotech.walmarthealthcareassignment.presentation.MyApp
import com.dotech.walmarthealthcareassignment.data.local.AppDatabase
import com.dotech.walmarthealthcareassignment.data.local.dao.CountryDao
import com.dotech.walmarthealthcareassignment.data.local.repositories.CountriesLocalRepo
import com.dotech.walmarthealthcareassignment.data.remote.apis.CountriesApi
import com.dotech.walmarthealthcareassignment.data.repositories.CountriesRepoImpl
import com.dotech.walmarthealthcareassignment.domain.repositories.CountriesRepo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule constructor(private val myApp: MyApp) {

    @Provides
    @Singleton
    fun getApplication() = myApp
    @Provides
    @Singleton
    fun providesApplicationContext(): Context = myApp

    @Provides
    @Singleton
    fun provideApiService() = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountriesApi::class.java)

    @Provides
    @Singleton
    fun provideCountriesRepo(context: Context,api: CountriesApi,countriesLocalRepo: CountriesLocalRepo):CountriesRepo {
        return CountriesRepoImpl(
            api = api,
            countriesLocalRepo = countriesLocalRepo,
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideDatabase(myApp: MyApp): AppDatabase =
        Room.databaseBuilder(
            myApp.baseContext,
            AppDatabase::class.java,
            AppDatabase::class.java.simpleName
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideQuotesDao(database: AppDatabase): CountryDao = database.countryDao()


}
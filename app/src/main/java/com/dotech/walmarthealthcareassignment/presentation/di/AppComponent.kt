package com.dotech.walmarthealthcareassignment.presentation.di

import com.dotech.walmarthealthcareassignment.presentation.MyApp
import com.dotech.walmarthealthcareassignment.presentation.ui.countries.CountriesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun appModule(module: AppModule): Builder
        fun build(): AppComponent
    }

    fun inject(app: MyApp)
    fun inject(countriesFragment: CountriesFragment)
}

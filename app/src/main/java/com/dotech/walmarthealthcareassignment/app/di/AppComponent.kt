package com.dotech.walmarthealthcareassignment.app.di

import com.dotech.walmarthealthcareassignment.app.MyApp
import com.dotech.walmarthealthcareassignment.app.ui.countries.CountriesFragment
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

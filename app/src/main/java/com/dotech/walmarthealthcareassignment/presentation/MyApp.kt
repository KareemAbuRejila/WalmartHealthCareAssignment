package com.dotech.walmarthealthcareassignment.presentation

import android.app.Application
import com.dotech.walmarthealthcareassignment.presentation.di.AppComponent
import com.dotech.walmarthealthcareassignment.presentation.di.AppModule
import com.dotech.walmarthealthcareassignment.presentation.di.DaggerAppComponent


class MyApp : Application(){
    companion object {
        private lateinit var appComponent: AppComponent

        fun getAppComponent(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerAppComponent()
    }

    private fun initDaggerAppComponent(): AppComponent {
        appComponent =
            DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        return appComponent
    }
}
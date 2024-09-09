package com.dotech.walmarthealthcareassignment.app

import android.app.Application
import com.dotech.walmarthealthcareassignment.app.di.AppComponent
import com.dotech.walmarthealthcareassignment.app.di.AppModule
import com.dotech.walmarthealthcareassignment.app.di.DaggerAppComponent


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
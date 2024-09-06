package com.dotech.walmarthealthcareassignment.app

import android.app.Application
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.LifecycleOwner
import com.dotech.walmarthealthcareassignment.app.network.NetworkConnectionMonitor
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApp : Application()
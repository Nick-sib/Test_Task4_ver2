package com.nickolay.testtask4ver2

import android.app.Application
import com.nickolay.testtask4ver2.data.DataProviderImpl
import com.nickolay.testtask4ver2.data.provider.DataProvider


class App: Application() {

    val dataProvider: DataProvider by lazy {
        DataProviderImpl()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    companion object {
        lateinit var instance : App
            private set
    }
}
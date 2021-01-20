package com.ulik.project.myplannerapp

import android.app.Application
import com.ulik.project.myplannerapp.utilities.di.domainModule
import com.ulik.project.myplannerapp.utilities.di.presenterModule
import com.ulik.project.myplannerapp.utilities.di.repoModule
import com.ulik.project.myplannerapp.utilities.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                domainModule,
                viewModel,
                repoModule,
                presenterModule

            )
        }
    }
}
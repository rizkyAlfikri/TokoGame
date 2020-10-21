package com.alfikri.rizky.tokogame

import android.app.Application
import com.alfikri.rizky.core.di.databaseModule
import com.alfikri.rizky.core.di.networkModule
import com.alfikri.rizky.core.di.repositoryModule
import com.alfikri.rizky.tokogame.di.feature.adapterModule
import com.alfikri.rizky.tokogame.di.feature.usecaseModule
import com.alfikri.rizky.tokogame.di.feature.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version MainApplication, v 0.1 9/23/2020 2:18 PM by Rizky Alfikri Rachmat
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            koin.loadModules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    usecaseModule,
                    viewModelModule,
                    adapterModule
                )
            )
        }
    }
}
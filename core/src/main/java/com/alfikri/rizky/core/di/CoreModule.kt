package com.alfikri.rizky.core.di

import androidx.room.Room
import com.alfikri.rizky.data.BuildConfig
import com.alfikri.rizky.data.source.GameEntityRepository
import com.alfikri.rizky.data.source.local.LocalEntityDataSource
import com.alfikri.rizky.data.source.local.room.GameDatabase
import com.alfikri.rizky.data.source.remote.RemoteEntityDataSource
import com.alfikri.rizky.data.source.remote.network.ApiService
import com.alfikri.rizky.domain.repository.GameRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version CoreModule, v 0.1 9/21/2020 12:20 PM by Rizky Alfikri Rachmat
 */

val databaseModule = module{
    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "Game.db"
        ).fallbackToDestructiveMigration().build()
    }
    factory { get<GameDatabase>().gameDao()  }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteEntityDataSource(get()) }
    single { LocalEntityDataSource(get()) }
    single <GameRepository>{ GameEntityRepository(get(), get())    }
}
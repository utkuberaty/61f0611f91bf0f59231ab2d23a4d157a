package com.path.stardelivery.di

import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.path.stardelivery.data.local.AppDatabase
import com.path.stardelivery.data.remote.RemoteDataSource
import com.path.stardelivery.data.remote.StationsService
import com.path.stardelivery.data.repository.SpaceShipRepository
import com.path.stardelivery.data.repository.StationRepository
import com.path.stardelivery.ui.create_ship.CreateShipViewModel
import com.path.stardelivery.ui.home.HomeViewModel
import com.path.stardelivery.ui.home.favourites.FavouritesViewModel
import com.path.stardelivery.ui.home.stations.StationsViewModel
import com.path.stardelivery.ui.root.RootViewModel
import com.path.stardelivery.ui.splash.SplashViewModel
import com.path.stardelivery.util.HOST
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .writeTimeout(20L, TimeUnit.SECONDS)
            .readTimeout(20L, TimeUnit.SECONDS)
            .connectTimeout(20L, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = get()
        Retrofit.Builder()
            .baseUrl(HOST)
            .client(client)
            .addConverterFactory(Json {
                prettyPrint = true
                isLenient = true
                coerceInputValues = true
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create(StationsService::class.java)
    }
}

val dataModule = module {
    single { RemoteDataSource(get()) }
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "starDelivery")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { (get() as AppDatabase).stationDao() }
}

val repositoryModule = module {
    single { StationRepository(get(), get()) }
    single { SpaceShipRepository(get()) }
}

val viewModelModule = module {
    viewModel { RootViewModel() }
    viewModel { SplashViewModel(get(), get()) }
    viewModel { HomeViewModel() }
    viewModel { StationsViewModel(get(), get()) }
    viewModel { FavouritesViewModel(get()) }
    viewModel { CreateShipViewModel(get(), get()) }
}
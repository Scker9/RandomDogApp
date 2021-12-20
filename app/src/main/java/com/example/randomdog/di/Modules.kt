package com.example.randomdog.di

import com.example.randomdog.data.repositories.DogFactRepositoryImpl
import com.example.randomdog.data.repositories.DogImageRepositoryImpl
import com.example.randomdog.data.response.source.DogFactApi
import com.example.randomdog.data.response.source.DogImageApi
import com.example.randomdog.domain.repositories.DogFactRepository
import com.example.randomdog.domain.repositories.DogImageRepository
import com.example.randomdog.domain.interactors.RandomDogInterractor
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Modules {
    val network = module {
        single {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }
        single<DogFactApi> {
            Retrofit.Builder()
                .baseUrl(dogFactBaseURL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
                .create(DogFactApi::class.java)
        }

        single<DogImageApi> {
            Retrofit.Builder()
                .baseUrl(dogImageBaseURL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
                .create(DogImageApi::class.java)
        }
    }
    val router = module {
        single { Cicerone.create() }
        single { get<Cicerone<Router>>().router }
        single { get<Cicerone<Router>>().getNavigatorHolder() }
    }
    val presenters = module {

    }
    val repos = module {
        factory<DogFactRepository> { DogFactRepositoryImpl() }
        factory<DogImageRepository> { DogImageRepositoryImpl() }
    }
    val interactors = module {
        single { RandomDogInterractor() }
    }
    val picasso = module {
        single { Picasso.with(get()) }
    }
    private const val dogImageBaseURL = "https://dog.ceo/api/breeds/image/"
    private const val dogFactBaseURL = "https://dog-facts-api.herokuapp.com/api/v1/resources/"
}
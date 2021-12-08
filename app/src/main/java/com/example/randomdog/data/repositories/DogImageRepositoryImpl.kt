package com.example.randomdog.data.repositories

import com.example.randomdog.data.response.DogImageModel
import com.example.randomdog.data.response.source.DogImageApi
import com.example.randomdog.domain.repositories.DogImageRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DogImageRepositoryImpl : DogImageRepository, KoinComponent {
    private val dogImageApi by inject<DogImageApi>()
    override fun getDogImage(): Single<DogImageModel> {
        return dogImageApi.getRandomDogImage().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
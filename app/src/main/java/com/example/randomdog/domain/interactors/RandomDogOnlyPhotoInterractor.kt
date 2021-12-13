package com.example.randomdog.domain.interactors

import com.example.randomdog.domain.repositories.DogImageRepository
import io.reactivex.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

class RandomDogOnlyPhotoInterractor:KoinComponent {
    private val dogImageRepo by inject<DogImageRepository>()
    fun getOneRandomDogImageUrl(): Single<String>
    {
        return dogImageRepo.getDogImage().map()
        {
            it.message
        }
    }
}
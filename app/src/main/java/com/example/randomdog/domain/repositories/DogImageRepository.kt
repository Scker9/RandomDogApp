package com.example.randomdog.domain.repositories

import com.example.randomdog.data.response.DogImageModel
import io.reactivex.Single

interface DogImageRepository {
    fun getDogImage(): Single<DogImageModel>
}
package com.example.randomdog.domain.repositories

import com.example.randomdog.data.response.DogFactModel
import io.reactivex.Single

interface DogFactRepository {
    fun getDogFact(): Single<DogFactModel>
}
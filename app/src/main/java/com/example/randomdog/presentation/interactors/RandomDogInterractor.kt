package com.example.randomdog.presentation.interactors

import com.example.randomdog.data.response.DogFactModel
import com.example.randomdog.data.response.DogImageModel
import com.example.randomdog.domain.entities.RandomDog
import com.example.randomdog.domain.repositories.DogFactRepository
import com.example.randomdog.domain.repositories.DogImageRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RandomDogInterractor : KoinComponent {
    private val dogImageRepo by inject<DogImageRepository>()
    private val dogFactRepo by inject<DogFactRepository>()
    fun getRandomDog(): Single<RandomDog> {
        return Single.zip(
            dogFactRepo.getDogFact(),
            dogImageRepo.getDogImage(),
            object : BiFunction<DogFactModel, DogImageModel, RandomDog> {
                override fun apply(t1: DogFactModel, t2: DogImageModel): RandomDog {
                    return RandomDog(t2.message, t1.fact)
                }
            })
    }
}

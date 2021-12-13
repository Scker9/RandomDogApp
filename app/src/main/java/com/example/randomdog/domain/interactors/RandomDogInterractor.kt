package com.example.randomdog.domain.interactors

import android.annotation.SuppressLint
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
    fun getOneRandomDog(): Single<RandomDog> {
        return Single.zip(
            dogFactRepo.getDogFact(),
            dogImageRepo.getDogImage(),
            object : BiFunction<DogFactModel, DogImageModel, RandomDog> {
                override fun apply(t1: DogFactModel, t2: DogImageModel): RandomDog {
                    return RandomDog(t2.message, t1.fact)
                }
            })
    }
//    @SuppressLint("CheckResult")
//    fun getRandomDogs(randomDogCount:Int):Single<List<RandomDog>>
//    {
//        val arrayListOfUrls= arrayListOf<RandomDog>()
//        for(i in 0 until randomDogCount)
//        {
//            dogImageRepo.getDogImage().map {
//                arrayListOfUrls.add(it.message)
//            }
//        }
//        dogFactRepo.getDogFacts(randomDogCount)
//            .map {
//                it.forEach {
//
//                }
//            }
//    }
}

package com.example.randomdog.domain.interactors

import com.example.randomdog.data.response.DogFactModel
import com.example.randomdog.domain.mappers.DogFactModelToString
import com.example.randomdog.domain.repositories.DogFactRepository
import io.reactivex.Observable
import io.reactivex.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RandomDogOnlyFactInterractor:KoinComponent {
    private val factRepo by inject<DogFactRepository>()
    fun getRandomDogFacts(factsCount:Int): Single<List<String>> = factRepo.getDogFacts(factsCount)
}
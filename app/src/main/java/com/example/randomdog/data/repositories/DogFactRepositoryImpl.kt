package com.example.randomdog.data.repositories

import com.example.randomdog.data.response.DogFactModel
import com.example.randomdog.data.response.source.DogFactApi
import com.example.randomdog.domain.repositories.DogFactRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DogFactRepositoryImpl : DogFactRepository, KoinComponent {
    private val dogFactApi by inject<DogFactApi>()
    override fun getDogFact(): Single<DogFactModel> {
        return dogFactApi.getOneDogFact().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).map {
                return@map it[0]
            }
    }

    override fun getDogFacts(numberOfFacts: Int): Observable<String> {
        return dogFactApi.getDogFacts(numberOfFacts).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapObservable {
                Observable.fromIterable(it)
            }
            .map {
                it.fact
            }
    }
}
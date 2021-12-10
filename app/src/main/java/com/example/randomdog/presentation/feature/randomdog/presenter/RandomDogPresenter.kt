package com.example.randomdog.presentation.feature.randomdog.presenter

import android.util.Log
import com.example.randomdog.presentation.base.BasePresenter
import com.example.randomdog.presentation.feature.randomdog.view.RandomDogView
import com.example.randomdog.presentation.interactors.RandomDogInterractor
import org.koin.core.component.inject

class RandomDogPresenter : BasePresenter<RandomDogView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateRandomDog()
    }
    private val randomDogInterractor by inject<RandomDogInterractor>()
    fun updateRandomDog() {
        randomDogInterractor.getRandomDog()
            .subscribe(
                {
                    viewState.showRandomDog(it)
                },
                {
                    Log.d("rand_dog_err", it.localizedMessage)
                }
            )
            .addToCompositeDisposable()
    }
}
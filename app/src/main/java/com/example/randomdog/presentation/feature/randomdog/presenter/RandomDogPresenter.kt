package com.example.randomdog.presentation.feature.randomdog.presenter

import android.util.Log
import com.example.randomdog.domain.entities.RandomDog
import com.example.randomdog.domain.entities.RandomDogBitmap
import com.example.randomdog.domain.interactors.RandomDogInterractor
import com.example.randomdog.presentation.base.BasePresenter
import com.example.randomdog.presentation.feature.randomdog.view.RandomDogView
import org.koin.core.component.inject

class RandomDogPresenter : BasePresenter<RandomDogView>() {
    private val randomDogInterractor by inject<RandomDogInterractor>()
    private val loadedDogs: ArrayList<RandomDogBitmap> = ArrayList()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getRandomDogs(2)
    }

    fun getRandomDogs(count: Int) {
        randomDogInterractor.getRandomDogs(count)
            .filter{
                !loadedDogs.contains(it)
            }
            .subscribe(
            {
                viewState.showLoading()
                loadedDogs.add(it)
                viewState.addNewRandomDogBitmap(loadedDogs)
            },
            {
                Log.d("1234",it.localizedMessage)
            },
            {
                viewState.loadingEnded()
            }
        ).addToCompositeDisposable()
    }

}
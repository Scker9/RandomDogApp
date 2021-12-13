package com.example.randomdog.presentation.feature.randomdog.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.example.randomdog.presentation.base.BasePresenter
import com.example.randomdog.presentation.feature.randomdog.view.RandomDogView
import com.example.randomdog.domain.interactors.RandomDogInterractor
import com.example.randomdog.domain.interactors.RandomDogOnlyFactInterractor
import org.koin.core.component.inject

class RandomDogPresenter : BasePresenter<RandomDogView>() {
    private val randomFactInterractor by inject<RandomDogOnlyFactInterractor>()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getFacts(10)
    }
    @SuppressLint("CheckResult")
    fun getFacts(factsCount:Int)
    {
        randomFactInterractor.getRandomDogFacts(factsCount).subscribe(
            {
                viewState.updateListOfFacts(it)
            },
            {

            }
        )
    }
}
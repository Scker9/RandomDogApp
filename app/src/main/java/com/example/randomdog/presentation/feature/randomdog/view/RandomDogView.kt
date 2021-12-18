package com.example.randomdog.presentation.feature.randomdog.view


import com.example.randomdog.domain.entities.RandomDogBitmap
import com.example.randomdog.presentation.base.BaseView
import moxy.viewstate.strategy.*

@StateStrategyType(AddToEndStrategy::class)
interface RandomDogView : BaseView {
    fun addNewRandomDogBitmap(list: List<RandomDogBitmap>)
    fun showLoading()
    fun loadingEnded()
}
package com.example.randomdog.presentation.feature.randomdog.view

import com.example.randomdog.domain.entities.RandomDog
import com.example.randomdog.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RandomDogView : BaseView {
    fun updateListOfFactsAndMarkLoadingEnded(list:List<String>)
    fun showLoading()
    fun loadindEnded()
}
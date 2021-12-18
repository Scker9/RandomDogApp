package com.example.randomdog.presentation.feature.randomdog.view

import android.graphics.Bitmap
import com.example.randomdog.domain.entities.RandomDog
import com.example.randomdog.domain.entities.RandomDogBitmap
import com.example.randomdog.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SkipStrategy::class)
interface RandomDogView : BaseView {
    fun addNewRandomDogBitmap(list: List<RandomDogBitmap>)
    fun showLoading()
    fun loadingEnded()
}
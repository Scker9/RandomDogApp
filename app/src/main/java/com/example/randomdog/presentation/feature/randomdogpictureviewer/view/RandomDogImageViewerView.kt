package com.example.randomdog.presentation.feature.randomdogpictureviewer.view

import android.graphics.Bitmap
import com.example.randomdog.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RandomDogImageViewerView:BaseView {
    fun addBitmapToViewer(bitmap: ArrayList<Bitmap>)
}
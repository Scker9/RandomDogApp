package com.example.randomdog.presentation.feature.randomdogpictureviewer.view

import com.example.randomdog.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface RandomDogImageViewerView:BaseView {
}
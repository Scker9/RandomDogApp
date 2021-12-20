package com.example.randomdog.presentation.feature.randomdogpictureviewer.presenter

import android.util.Log
import com.example.randomdog.domain.interactors.RandomDogInterractor
import com.example.randomdog.presentation.base.BasePresenter
import com.example.randomdog.presentation.feature.randomdog.presenter.RandomDogPresenter
import com.example.randomdog.presentation.feature.randomdogpictureviewer.view.RandomDogImageViewerView
import org.koin.core.component.inject

class RandomDogImageViewerPresenter : BasePresenter<RandomDogImageViewerView>() {
    val randomDogInterractor by inject<RandomDogInterractor>()
    val TAG = this::class.java.simpleName
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getDogPics()
    }

    private fun getDogPics() {
        randomDogInterractor.getAlreadyLoadedImages().subscribe(
            {
                viewState.addBitmapToViewer(it)
            },
            {

            },
            {

            }
        ).addToCompositeDisposable()
    }
}
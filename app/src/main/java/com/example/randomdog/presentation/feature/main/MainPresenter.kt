package com.example.randomdog.presentation.feature.main

import moxy.MvpPresenter

class MainPresenter : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.moveToRandomDogFragment()
    }
}
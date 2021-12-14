package com.example.randomdog

import com.example.randomdog.presentation.feature.randomdog.view.RandomDogFragment
import com.example.randomdog.presentation.feature.randomdogpictureviewer.view.RandomDogImageViewerFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    val FRAGMENT_RANDOM_DOG = FragmentScreen { RandomDogFragment.newInstance() }
    val FRAGMENT_RANDOM_DOG_IMAGE_VIEWER = FragmentScreen { RandomDogImageViewerFragment.newInstance()}
}
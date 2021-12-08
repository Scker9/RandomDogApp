package com.example.randomdog

import com.example.randomdog.presentation.feature.randomdog.view.RandomDogFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    val FRAGMENT_RANDOM_DOG = FragmentScreen { RandomDogFragment.newInstance() }
}
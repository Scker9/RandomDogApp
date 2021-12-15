package com.example.randomdog

import android.graphics.Bitmap
import com.example.randomdog.presentation.feature.randomdog.view.RandomDogFragment
import com.example.randomdog.presentation.feature.randomdogpictureviewer.view.RandomDogImageViewerFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import java.text.FieldPosition

object Screens {
    val FRAGMENT_RANDOM_DOG = FragmentScreen { RandomDogFragment.newInstance() }
    fun FRAGMENT_RANDOM_DOG_IMAGE_VIEWER(list: List<Bitmap>, position: Int) =
        FragmentScreen { RandomDogImageViewerFragment.newInstance(list, position) }
}
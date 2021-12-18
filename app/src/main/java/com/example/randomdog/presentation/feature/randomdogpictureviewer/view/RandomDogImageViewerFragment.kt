package com.example.randomdog.presentation.feature.randomdogpictureviewer.view

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.graphics.drawable.toBitmap
import androidx.viewpager2.widget.ViewPager2
import com.example.randomdog.R
import com.example.randomdog.Screens
import com.example.randomdog.databinding.RandomDogImageViewerBinding
import com.example.randomdog.presentation.base.BaseFragment
import com.example.randomdog.presentation.feature.randomdogpictureviewer.adapter.RandomDogImageViewAdapter
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.android.inject
import java.text.FieldPosition

class RandomDogImageViewerFragment : BaseFragment<RandomDogImageViewerBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RandomDogImageViewerBinding =
        RandomDogImageViewerBinding::inflate
    private var viewPager: ViewPager2? = null
    private val adapter by lazy { RandomDogImageViewAdapter() }
    var bitmaps: List<Bitmap> = ArrayList()
    var startScrollPosition: Int = 0
    private val router by inject<Router>()

    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    router.exit()
                }
            })
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.imageDogViewPager)
        viewPager?.adapter = adapter
        adapter.setData(bitmaps)
        viewPager?.setCurrentItem(startScrollPosition, false)
    }

    companion object {
        fun newInstance(list: List<Bitmap>, position: Int): RandomDogImageViewerFragment {
            val temp = RandomDogImageViewerFragment()
            temp.bitmaps = list
            temp.startScrollPosition = position
            return temp
        }
    }
}
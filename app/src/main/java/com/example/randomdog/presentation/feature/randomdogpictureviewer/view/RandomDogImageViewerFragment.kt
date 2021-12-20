package com.example.randomdog.presentation.feature.randomdogpictureviewer.view

import ZoomOutPageTransformer
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.viewpager2.widget.ViewPager2
import com.example.randomdog.R
import com.example.randomdog.Screens
import com.example.randomdog.databinding.RandomDogImageViewerBinding
import com.example.randomdog.domain.entities.RandomDog
import com.example.randomdog.presentation.base.BaseFragment
import com.example.randomdog.presentation.feature.randomdogpictureviewer.adapter.RandomDogImageViewAdapter
import com.example.randomdog.presentation.feature.randomdogpictureviewer.presenter.RandomDogImageViewerPresenter
import com.github.terrakok.cicerone.Router
import moxy.presenter.InjectPresenter
import org.koin.android.ext.android.inject

class RandomDogImageViewerFragment : BaseFragment<RandomDogImageViewerBinding>(),
    RandomDogImageViewerView {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RandomDogImageViewerBinding =
        RandomDogImageViewerBinding::inflate
    private val TAG = this::class.java.simpleName
    private var viewPager: ViewPager2? = null

    @InjectPresenter
    lateinit var presenter: RandomDogImageViewerPresenter
    private val adapter by lazy { RandomDogImageViewAdapter() }
    var bitmaps: List<Bitmap> = ArrayList()
    var startScrollPosition: Int = 0
    private val router by inject<Router>()

    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
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
        viewPager?.setPageTransformer(ZoomOutPageTransformer())
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (isItLastViewPagerPosition(position)) {
                    Log.d(TAG, "last element")
                }
                super.onPageSelected(position)
            }
        })
        viewPager?.adapter = adapter
        adapter.onSwipeItemBottomAndTop =
            {
                router.exit()
            }
    }

    companion object {
        fun newInstance(position: Int): RandomDogImageViewerFragment {
            val temp = RandomDogImageViewerFragment()
            temp.startScrollPosition = position
            return temp
        }
    }

    override fun addBitmapToViewer(bitmap: ArrayList<Bitmap>) {
        adapter.setData(bitmap)
        scrollToNewPosition(startScrollPosition)
    }

    private fun scrollToNewPosition(position: Int) {
        viewPager?.setCurrentItem(startScrollPosition, false)
    }

    private fun isItLastViewPagerPosition(position: Int): Boolean {
        return ((position == adapter.itemCount - 1))
    }
}
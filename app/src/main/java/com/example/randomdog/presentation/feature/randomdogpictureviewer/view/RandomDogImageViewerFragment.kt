package com.example.randomdog.presentation.feature.randomdogpictureviewer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.viewpager2.widget.ViewPager2
import com.example.randomdog.R
import com.example.randomdog.databinding.RandomDogImageViewerBinding
import com.example.randomdog.presentation.base.BaseFragment
import com.example.randomdog.presentation.feature.randomdogpictureviewer.adapter.RandomDogImageViewAdapter

class RandomDogImageViewerFragment : BaseFragment<RandomDogImageViewerBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RandomDogImageViewerBinding =
        RandomDogImageViewerBinding::inflate
    var viewPager: ViewPager2? = null
    private val adapter by lazy { RandomDogImageViewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.imageDogViewPager)
        viewPager?.adapter = adapter
        adapter.setData(
            listOf(
                resources.getDrawable(R.drawable.ic_launcher_background).toBitmap(),
                resources.getDrawable(R.drawable.ic_launcher_foreground).toBitmap()
            )
        )
    }

    companion object {
        fun newInstance(): RandomDogImageViewerFragment = RandomDogImageViewerFragment()
    }
}
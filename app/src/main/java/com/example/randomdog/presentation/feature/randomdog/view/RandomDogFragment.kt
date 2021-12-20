package com.example.randomdog.presentation.feature.randomdog.view

import com.example.randomdog.presentation.feature.randomdogpictureviewer.adapter.swipe.OnSwipeTouchListener
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomdog.R
import com.example.randomdog.Screens
import com.example.randomdog.databinding.RandomDogFragmentBinding
import com.example.randomdog.domain.entities.RandomDogBitmap
import com.example.randomdog.presentation.base.BaseFragment
import com.example.randomdog.presentation.feature.randomdog.adapter.RandomDogAdapter
import com.example.randomdog.presentation.feature.randomdog.presenter.RandomDogPresenter
import com.github.terrakok.cicerone.Router
import moxy.presenter.InjectPresenter
import org.koin.android.ext.android.inject

class RandomDogFragment : BaseFragment<RandomDogFragmentBinding>(), RandomDogView {
    @InjectPresenter
    lateinit var presenter: RandomDogPresenter
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RandomDogFragmentBinding =
        RandomDogFragmentBinding::inflate
    private val adapter by lazy { RandomDogAdapter() }
    private val router by inject<Router>()
    private val TAG = this::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    //doing nothing
                }
            })
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeToRefresh.isEnabled = false
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        binding.randomDogRecycler.addItemDecoration(dividerItemDecoration)
        binding.randomDogRecycler.adapter = adapter
        adapter.onPictureClicked =
            { position ->
                moveToPictureViewer(position)
            }
        binding.randomDogRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && isLastVisible()) {
                    binding.swipeToRefresh.isRefreshing = true
                    presenter.getRandomDogs(RandomDogPresenter.DOGS_COUNT_DEFAULT)
                }
            }
        })
    }

    override fun addNewRandomDogBitmap(list: List<RandomDogBitmap>) {
        adapter.setItems(list as ArrayList<RandomDogBitmap>)
    }


    override fun showLoading() {
        binding.swipeToRefresh.isRefreshing = true
    }

    override fun loadingEnded() {
        binding.swipeToRefresh.isRefreshing = false
    }

    private fun moveToPictureViewer(position: Int) {
        router.navigateTo(
            Screens.FRAGMENT_RANDOM_DOG_IMAGE_VIEWER(
                position
            )
        )
    }

    private fun isLastVisible(): Boolean {
        val layoutManager = binding.randomDogRecycler.layoutManager as LinearLayoutManager
        val pos = layoutManager.findLastCompletelyVisibleItemPosition()
        val numItems = adapter.itemCount
        return (pos >= numItems - 1)
    }

    companion object {
        fun newInstance(): RandomDogFragment = RandomDogFragment()
    }

}
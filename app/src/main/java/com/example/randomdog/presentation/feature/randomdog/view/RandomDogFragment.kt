package com.example.randomdog.presentation.feature.randomdog.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomdog.Screens
import com.example.randomdog.databinding.RandomDogFragmentBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    //doing nothing
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeToRefresh.isEnabled=false
        binding.randomDogRecycler.adapter = adapter
        binding.randomDogRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && isLastVisible()) {
                    binding.swipeToRefresh.isRefreshing=true
                    presenter.getFacts(10)
                }
            }
        })
        adapter.navigationCallBack =
            {
               // moveToPictureViewer() //доделать исправить
            }
    }

    override fun updateListOfFactsAndMarkLoadingEnded(list: List<String>) {
        adapter.setItems(list)
        loadindEnded()
    }

    override fun showLoading() {
        binding.swipeToRefresh.isRefreshing=true
    }

    override fun loadindEnded() {
        binding.swipeToRefresh.isRefreshing=false
    }

    private fun moveToPictureViewer() {
        router.navigateTo(Screens.FRAGMENT_RANDOM_DOG_IMAGE_VIEWER)
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
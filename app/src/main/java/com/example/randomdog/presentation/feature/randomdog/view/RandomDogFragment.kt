package com.example.randomdog.presentation.feature.randomdog.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomdog.R
import com.example.randomdog.databinding.RandomDogFragmentBinding
import com.example.randomdog.domain.entities.RandomDog
import com.example.randomdog.presentation.base.BaseFragment
import com.example.randomdog.presentation.feature.randomdog.RandomDogAdapter
import com.example.randomdog.presentation.feature.randomdog.presenter.RandomDogPresenter
import com.squareup.picasso.Picasso
import moxy.presenter.InjectPresenter

class RandomDogFragment : BaseFragment<RandomDogFragmentBinding>(), RandomDogView {
    @InjectPresenter
    lateinit var presenter: RandomDogPresenter
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RandomDogFragmentBinding =
        RandomDogFragmentBinding::inflate
    private var recycler:RecyclerView?=null
    private val adapter by lazy { RandomDogAdapter()  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                }
            })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler= view.findViewById(R.id.randomDogRecycler)
        recycler?.adapter=adapter
        recycler?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    if (isLastVisable()) {
                        presenter.getFacts(10)
                    }
                }
            }
        })
    }
    companion object {
        fun newInstance(): RandomDogFragment = RandomDogFragment()
    }

    override fun updateListOfFacts(list: List<String>) {
        adapter.setItems(list)
    }

    private fun isLastVisable(): Boolean {
        val layoutManager = recycler?.layoutManager as LinearLayoutManager
        val pos = layoutManager.findLastCompletelyVisibleItemPosition()
        val numItems = adapter.itemCount
        return (pos >= numItems - 1)
    }
}
package com.example.randomdog.presentation.feature.randomdog.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.randomdog.databinding.RandomDogFragmentBinding
import com.example.randomdog.domain.entities.RandomDog
import com.example.randomdog.presentation.base.BaseFragment
import com.example.randomdog.presentation.feature.randomdog.presenter.RandomDogPresenter
import com.squareup.picasso.Picasso
import moxy.presenter.InjectPresenter

class RandomDogFragment : BaseFragment<RandomDogFragmentBinding>(), RandomDogView {
    @InjectPresenter
    lateinit var presenter: RandomDogPresenter
    private var picasso: Picasso? = null
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RandomDogFragmentBinding =
        RandomDogFragmentBinding::inflate
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.refreshRandomDog.setOnClickListener {
            presenter.updateRandomDog()
            Toast.makeText(requireContext(), "Getting a new gog, pls wait...", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun showRandomDog(randomDog: RandomDog) {
        binding.dogFactTextView.text = randomDog.fact
        picasso = Picasso.with(requireContext())
        picasso?.load(randomDog.image)?.into(binding.randomDogImageView)
    }

    companion object {
        fun newInstance(): RandomDogFragment = RandomDogFragment()
    }

}
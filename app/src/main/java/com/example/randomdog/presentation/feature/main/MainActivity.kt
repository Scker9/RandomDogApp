package com.example.randomdog.presentation.feature.main

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.randomdog.R
import com.example.randomdog.Screens
import com.example.randomdog.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import org.koin.android.ext.android.inject


class MainActivity : MvpAppCompatActivity(), MainView {
    private val navigatorHolder by inject<NavigatorHolder>()
    private val router by inject<Router>()
    private val TAG = this::class.java.simpleName
    private lateinit var binding: ActivityMainBinding

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        //binding.mainContainer
        setContentView(view)
    }

    override fun moveToRandomDogFragment() {
        val navigator = AppNavigator(this, R.id.main_container)
        navigatorHolder.setNavigator(navigator)
        router.newRootScreen(Screens.FRAGMENT_RANDOM_DOG)
    }

}

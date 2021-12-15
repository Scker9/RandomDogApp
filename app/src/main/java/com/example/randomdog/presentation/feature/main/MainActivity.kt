package com.example.randomdog.presentation.feature.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.randomdog.R
import com.example.randomdog.Screens
import com.example.randomdog.data.repositories.DogFactRepositoryImpl
import com.example.randomdog.data.response.DogImageModel
import com.example.randomdog.data.response.source.DogFactApi
import com.example.randomdog.data.response.source.DogImageApi
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import org.koin.android.ext.android.inject

class MainActivity : MvpAppCompatActivity(), MainView {
    private val navigatorHolder by inject<NavigatorHolder>()
    private val router by inject<Router>()
    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun moveToRandomDogFragment() {
        val navigator = AppNavigator(this, R.id.main_container)
        navigatorHolder.setNavigator(navigator)
        router.navigateTo(Screens.FRAGMENT_RANDOM_DOG)
    }
}

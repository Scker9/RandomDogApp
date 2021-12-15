package com.example.randomdog.data.repositories

import android.graphics.Bitmap
import android.util.Log
import com.example.randomdog.data.response.DogImageModel
import com.example.randomdog.data.response.source.DogImageApi
import com.example.randomdog.domain.repositories.DogImageRepository
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject

class DogImageRepositoryImpl : DogImageRepository, KoinComponent {
    private val dogImageApi by inject<DogImageApi>()
    private val picasso by inject<Picasso>()

    override fun getDogImage(): Single<DogImageModel> {
        return dogImageApi.getOneRandomDogImage().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getDogImages(imagesCount: Int): Observable<Bitmap> {
        return dogImageApi.getRandomDogImages(imagesCount).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .map {
                it.message
            }
            .flatMap {
                getBitmap(it)
            }
    }

    private fun getBitmap(urls: List<String>): Observable<Bitmap> {
        return Observable.fromIterable(urls)
            .map {
                picasso.load(it).get()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
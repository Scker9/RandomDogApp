package com.example.randomdog.domain.interactors

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import com.example.randomdog.data.response.DogFactModel
import com.example.randomdog.data.response.DogImageModel
import com.example.randomdog.domain.entities.RandomDog
import com.example.randomdog.domain.entities.RandomDogBitmap
import com.example.randomdog.domain.mappers.ListsOfStringToListOfRandomDogMapper
import com.example.randomdog.domain.mappers.RandomDogBitmapMapper
import com.example.randomdog.domain.repositories.DogFactRepository
import com.example.randomdog.domain.repositories.DogImageRepository
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RandomDogInterractor : KoinComponent {
    private val TAG = this::class.java.simpleName
    private val dogImageRepo by inject<DogImageRepository>()
    private val dogFactRepo by inject<DogFactRepository>()
    private val loadedDogsImages: ArrayList<Bitmap> = arrayListOf()
    fun getRandomDogs(dogsCount: Int): Observable<RandomDogBitmap> {
        return Observable.zip(
            dogFactRepo.getDogFacts(dogsCount),
            dogImageRepo.getDogImages(dogsCount),
            object : BiFunction<String, Bitmap, RandomDogBitmap> {
                override fun apply(t1: String, t2: Bitmap): RandomDogBitmap {
                    return RandomDogBitmap(t1, t2)
                }
            }
        ).doOnNext {
            loadedDogsImages.add(it.bitmap)
            Log.d(TAG, loadedDogsImages.toString())
        }
    }

    fun getAlreadyLoadedImages(): Observable<ArrayList<Bitmap>> {
        return Observable.just(loadedDogsImages)
    }
}



package com.example.randomdog.domain.repositories

import android.graphics.Bitmap
import com.example.randomdog.data.response.DogImageModel
import io.reactivex.Observable
import io.reactivex.Single

interface DogImageRepository {
    fun getDogImage(): Single<DogImageModel>
    fun getDogImages(imagesCount:Int): Observable<Bitmap>
}
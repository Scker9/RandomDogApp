package com.example.randomdog.data.response.source

import com.example.randomdog.data.response.DogImageModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DogImageApi {
    //https://dog.ceo/api/breeds/image/random
    @GET("random")
    fun getOneRandomDogImage(): Single<DogImageModel>
    @GET("random/{imageCount}")
    fun getRandomDogImages(@Path("imageCount")imageCount:Int):Single<DogImageModel>
}
package com.example.randomdog.data.response.source

import com.example.randomdog.data.response.DogImageModel
import io.reactivex.Single
import retrofit2.http.GET

interface DogImageApi {
    //https://dog.ceo/api/breeds/image/random
    @GET("random")
    fun getRandomDogImage(): Single<DogImageModel>
}
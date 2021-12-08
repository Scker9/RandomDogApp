package com.example.randomdog.data.response.source

import com.example.randomdog.data.response.DogFactModel
import io.reactivex.Single
import retrofit2.http.GET

interface DogFactApi {
    //https://dog-facts-api.herokuapp.com/api/v1/resources/dogs?number=1
    @GET("dogs?number=1")
    fun getOneDogFact(): Single<List<DogFactModel>>
}
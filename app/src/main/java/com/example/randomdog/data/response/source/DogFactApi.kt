package com.example.randomdog.data.response.source

import com.example.randomdog.data.response.DogFactModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DogFactApi {
    //https://dog-facts-api.herokuapp.com/api/v1/resources/dogs?number=1
    @GET("dogs?number=1")
    fun getOneDogFact(): Single<List<DogFactModel>>
    @GET("dogs?")
    fun getDogFacts(@Query("number") numberOfFacts:Int):Single<List<DogFactModel>>
}
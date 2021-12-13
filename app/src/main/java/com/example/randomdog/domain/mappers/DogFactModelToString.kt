package com.example.randomdog.domain.mappers

import com.example.randomdog.data.response.DogFactModel

class DogFactModelToString {
    fun convert(list: List<DogFactModel>):List<String>
    {
        val temp = ArrayList<String>()
        list.forEach {
            temp.add(it.fact)
        }
        return temp
    }
}
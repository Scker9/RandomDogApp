package com.example.randomdog.domain.mappers

import com.example.randomdog.domain.entities.RandomDog

class ListsOfStringToListOfRandomDogMapper {
    fun convert(facts: List<String>, images: List<String>): List<RandomDog> {
        val temp = ArrayList<RandomDog>()
        if(facts.size==images.size)
        {
            for (i in facts.indices) {
                temp.add(RandomDog(images[i], facts[i]))
            }
        }
        return temp
    }
}
package com.example.randomdog.domain.mappers

import android.graphics.Bitmap
import com.example.randomdog.domain.entities.RandomDog
import com.example.randomdog.domain.entities.RandomDogBitmap

class RandomDogBitmapMapper {
    fun convert(facts: List<String>, bitmaps: List<Bitmap>): List<RandomDogBitmap> {
        val temp = ArrayList<RandomDogBitmap>()
        if (facts.size == bitmaps.size) {
            for (i in facts.indices) {
                temp.add(RandomDogBitmap(fact = facts[i], bitmap = bitmaps[i]))
            }
        } else {
            for (i in bitmaps.indices) {
                temp.add(RandomDogBitmap(fact = facts[i], bitmap = bitmaps[i]))
            }
        }
        return temp
    }
}
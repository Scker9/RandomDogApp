package com.example.randomdog.data.response

import androidx.annotation.Keep

@Keep
data class DogFactModel(
    val fact: String
)
{
    override fun toString(): String {
        return fact
    }
}
package com.example.randomdog.presentation.feature.randomdogpictureviewer.adapter

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.randomdog.R

class RandomDogImageViewAdapter :
    RecyclerView.Adapter<RandomDogImageViewAdapter.DogImageViewHolder>() {
    private var items: List<Bitmap> = arrayListOf()

    class DogImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pictureOfDog = itemView.findViewById<ImageView>(R.id.big_dog_image)
        fun bind(bitMap: Bitmap) {
            Log.d("dsadsa", "bind")

            pictureOfDog.setImageBitmap(bitMap)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogImageViewHolder {
        Log.d("dsadsa", "create")

        return DogImageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.random_dog_image_viewer_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DogImageViewHolder, position: Int) {
        Log.d("dsadsa", "onBind")
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.count()

    fun setData(newData: List<Bitmap>)
    {
        Log.d("dsadsa", "data")
        items=newData
    }
}
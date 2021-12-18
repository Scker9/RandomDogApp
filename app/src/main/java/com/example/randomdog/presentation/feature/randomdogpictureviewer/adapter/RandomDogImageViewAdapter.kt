package com.example.randomdog.presentation.feature.randomdogpictureviewer.adapter

import com.example.randomdog.presentation.feature.randomdogpictureviewer.adapter.swipe.OnSwipeTouchListener
import android.annotation.SuppressLint
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
    private val TAG = this::class.java.simpleName
    var onSwipeItemBottomAndTop: (() -> Unit)? = null
    private var items: List<Bitmap> = arrayListOf()

    inner class DogImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pictureOfDog = itemView.findViewById<ImageView>(R.id.big_dog_image)

        @SuppressLint("ClickableViewAccessibility")
        fun bind(bitMap: Bitmap) {
            pictureOfDog.setImageBitmap(bitMap)
            itemView.setOnTouchListener(object :
                OnSwipeTouchListener(itemView.context) {
                override fun onSwipeTop() {
                    onSwipeItemBottomAndTop?.invoke()
                    Log.d(TAG, "TOP ")
                }

                override fun onSwipeBottom() {
                    onSwipeItemBottomAndTop?.invoke()
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogImageViewHolder {
        return DogImageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.random_dog_image_viewer_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DogImageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.count()

    fun setData(newData: List<Bitmap>) {
        items = newData
    }
}
package com.example.randomdog.presentation.feature.randomdog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.randomdog.R
import com.example.randomdog.domain.entities.RandomDogBitmap
import org.koin.core.component.KoinComponent

class RandomDogAdapter : RecyclerView.Adapter<RandomDogAdapter.RandomDogViewHolder>(),
    KoinComponent {
    var dataitems: ArrayList<RandomDogBitmap> = ArrayList()
    var onPictureClicked: ((Int) -> Unit)? = null

    inner class RandomDogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val factTextView: TextView = itemView.findViewById(R.id.randomDogFactItemTextView)
        private val imageDog: ImageView = itemView.findViewById(R.id.randomDogItemImageView)
        fun bind(randomDog: RandomDogBitmap) {
            factTextView.text = randomDog.fact
            imageDog.setImageBitmap(randomDog.bitmap)
            imageDog.setOnClickListener {
                onPictureClicked?.invoke(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomDogViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.random_dog_recycler_item, parent, false)
        return RandomDogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RandomDogViewHolder, position: Int) {
        holder.bind(dataitems[position])
    }

    override fun getItemCount(): Int = dataitems.count()

    fun setItems(newItems: ArrayList<RandomDogBitmap>) {
        dataitems = newItems
        notifyDataSetChanged()
    }

}
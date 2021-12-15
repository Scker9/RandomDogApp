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
    private var items: ArrayList<RandomDogBitmap> = ArrayList()
    var onPictureClicked: (() -> Unit)? = null

    inner class RandomDogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val factTextView: TextView = itemView.findViewById(R.id.randomDogFactItemTextView)
        private val imageDog: ImageView = itemView.findViewById(R.id.randomDogItemImageView)
        fun bind(randomDog: RandomDogBitmap) {
            factTextView.text = randomDog.fact
            imageDog.setImageBitmap(randomDog.bitmap)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomDogViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.random_dog_recycler_item, parent, false)
        itemView.findViewById<ImageView>(R.id.randomDogItemImageView).setOnClickListener {
            onPictureClicked?.invoke()
        }
        return RandomDogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RandomDogViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.count()

    fun setItems(newItems: List<RandomDogBitmap>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

}
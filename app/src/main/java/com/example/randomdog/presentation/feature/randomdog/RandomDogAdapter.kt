package com.example.randomdog.presentation.feature.randomdog

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.randomdog.R
import com.example.randomdog.domain.entities.RandomDog
import com.example.randomdog.domain.interactors.RandomDogOnlyPhotoInterractor
import com.squareup.picasso.Picasso
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RandomDogAdapter : RecyclerView.Adapter<RandomDogAdapter.RandomDogViewHolder>(),
    KoinComponent {
    private val randomDogInterractor by inject<RandomDogOnlyPhotoInterractor>()
    private var items: ArrayList<RandomDog> = ArrayList()

    class RandomDogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val factTextView: TextView = itemView.findViewById(R.id.randomDogFactItemTextView)
        private val imageDog: ImageView = itemView.findViewById(R.id.randomDogItemImageView)
        private val picasso: Picasso = Picasso.with(itemView.context)
        fun bind(randomDog: RandomDog) {
            factTextView.text = randomDog.fact
            picasso.load(randomDog.image).into(imageDog)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomDogViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.random_dog_recycler_item, parent, false)
        val viewHolder = RandomDogViewHolder(itemView)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RandomDogViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.count()

    @SuppressLint("CheckResult", "NotifyDataSetChanged")
    fun setItems(facts: List<String>) {
        facts.forEach { fact ->
            randomDogInterractor.getOneRandomDogImageUrl().subscribe(
                { imageUrl ->
                    items.add(RandomDog(imageUrl, fact))
                    notifyDataSetChanged()
                },
                {
                    Log.d("errors",it.localizedMessage)
                }
            )
        }
    }
}
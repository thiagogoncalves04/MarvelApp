package com.example.marvelappkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.example.marvelappkotlin.model.Character
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.marvelappkotlin.OnImageClick
import com.example.marvelappkotlin.R
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(var characters: MutableList<Character>, val listener: OnImageClick, val context: Context) :
    RecyclerView.Adapter<CharacterAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(p0: CharacterAdapter.VH, p1: Int) {
        p0.bind(characters[p1])
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var imageView: ImageView
        fun bind(character: Character) {
            imageView = itemView.findViewById(R.id.image_character)
            itemView.text_item_character.text = character?.name

            Glide.with(context)
                .load("${character?.thumbnail?.path}/standard_medium.${character?.thumbnail?.extension}")
                .into(imageView)
            itemView.setOnClickListener {
                listener.onCardClicked(character)
            }
            itemView.setOnClickListener { listener.onCardClicked(character) }
        }
    }
}
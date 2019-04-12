package com.example.marvelappkotlin

import com.example.marvelappkotlin.model.Character

interface OnImageClick {
    fun onCardClicked(character: Character)
}
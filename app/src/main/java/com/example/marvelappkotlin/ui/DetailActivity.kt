package com.example.marvelappkotlin.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.marvelappkotlin.R
import com.example.marvelappkotlin.model.Character
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var character: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initViews()

        intent.extras?.get("detalhe")?.also {
            character = it as Character
            title_character.text = character.name
            description_character?.text = character.description
            Glide.with(this)
                .load("${character?.thumbnail?.path}/standard_medium.${character?.thumbnail?.extension}").into(imageDetailCharacter)
        } ?: run {
            finish()
        }
    }

    private fun initViews() {
        backPage()
    }

    fun backPage(){
        button_backpage.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
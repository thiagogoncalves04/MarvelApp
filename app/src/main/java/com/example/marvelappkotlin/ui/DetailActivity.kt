package com.example.marvelappkotlin.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelappkotlin.R
import com.example.marvelappkotlin.model.Character
import kotlinx.android.synthetic.main.item_character.*

class DetailActivity : AppCompatActivity() {

    private lateinit var character: com.example.marvelappkotlin.model.Character
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)
//
//        // Recupera o character que foi eviado da tela anterior
//        intent.extras?.get("detalhe")?.also {
//            character = it as Character
//        } ?: run {
//            // Não recebeu o character
//            finish()
//        }
//
//        initViews()
//    }
//
//    private fun initViews() {
//        text_character.text = character.name
//    }
}


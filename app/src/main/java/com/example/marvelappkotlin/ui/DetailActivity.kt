package com.example.marvelappkotlin.ui

import android.support.v7.app.AppCompatActivity

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


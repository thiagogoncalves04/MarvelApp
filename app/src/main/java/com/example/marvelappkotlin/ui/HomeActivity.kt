package com.example.marvelappkotlin.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.marvelappkotlin.HomeContract
import com.example.marvelappkotlin.OnImageClick
import com.example.marvelappkotlin.R
import com.example.marvelappkotlin.adapter.CharacterAdapter
import com.example.marvelappkotlin.model.Character
import com.example.marvelappkotlin.presenter.HomePresenter
import kotlinx.android.synthetic.main.home_main.*

class HomeActivity : AppCompatActivity(), OnImageClick, HomeContract.View {

    lateinit var adapter: CharacterAdapter
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_main)

        presenter = HomePresenter()
        adapter = CharacterAdapter(ArrayList(), this, this)
        initView()
    }

    override fun initView() {
        setupRecyclerView()
        presenter.create(this)
        showLoading()
    }

    override fun setupRecyclerView() {
        val llm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerCharacters.layoutManager = llm
        recyclerCharacters.adapter = adapter
    }

    override fun showCharacter(listCharacter: ArrayList<Character>) {
        adapter.characters.clear()
        adapter.characters.addAll(listCharacter)
        adapter.notifyDataSetChanged()
    }

    override fun onCardClicked(character: Character) {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra("detalhe", character)
        })
    }

    override fun showLoading() {
    }
}


package com.example.marvelappkotlin.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.marvelappkotlin.OnImageClick
import com.example.marvelappkotlin.R
import com.example.marvelappkotlin.adapter.CharactersAdapter
import com.example.marvelappkotlin.model.Character
import com.example.marvelappkotlin.presenter.HomePresenter
import kotlinx.android.synthetic.main.home_main.*

class HomeActivity : AppCompatActivity(), OnImageClick {

    lateinit var adapter: CharactersAdapter
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_main)

        presenter = HomePresenter()
        adapter = CharactersAdapter(ArrayList(), this, this)
        initView()
    }

    fun initView(){
        setupRecyclerView();
        presenter.create(this)
    }

    fun setupRecyclerView(){
        val llm = LinearLayoutManager(this)
        recyclerCharacters.layoutManager = llm
        recyclerCharacters.adapter = adapter
    }

    override fun onCardClicked(character: Character) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun showCharacter(listCharacter: ArrayList<Character>){
        adapter.characters.clear()
        adapter.characters.addAll(listCharacter)
        adapter.notifyDataSetChanged()
    }
}
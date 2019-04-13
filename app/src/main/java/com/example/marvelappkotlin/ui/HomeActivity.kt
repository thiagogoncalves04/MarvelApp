package com.example.marvelappkotlin.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.example.marvelappkotlin.*
import com.example.marvelappkotlin.adapter.CharacterAdapter
import com.example.marvelappkotlin.model.Character
import com.example.marvelappkotlin.presenter.HomePresenter
import kotlinx.android.synthetic.main.home_main.*

class HomeActivity : AppCompatActivity(), OnImageClick, HomeContract.View, LoadMore {

    lateinit var adapter: CharacterAdapter
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_main)

        presenter = HomePresenter()
        adapter = CharacterAdapter(ArrayList(), this, this)
        initView()

        setSupportActionBar(toolbar as Toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId){
            R.id.sort_by_date -> {
                presenter.sortByDate()
            }
        }
        return true
    }

    override fun initView() {
        showLoading()
        setupRecyclerView()
        presenter.create(this)
    }

    override fun setupRecyclerView() {
        val llm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerCharacters.layoutManager = llm
        recyclerCharacters.addOnScrollListener(ScrollListener(llm, this))
        recyclerCharacters.adapter = adapter
    }

    override fun showCharacter(listCharacter: ArrayList<Character>) {
        adapter.characters.clear()
        adapter.characters.addAll(listCharacter)
        hideLoading()
        adapter.notifyDataSetChanged()
    }

    override fun onCardClicked(character: Character) {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra("detalhe", character)
        })
    }

    override fun onLoadMore() {
        presenter.loadMore()
    }

    override fun showLoading() {
        val loading = findViewById<ProgressBar>(R.id.loading)
        loading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        val loading = findViewById<ProgressBar>(R.id.loading)
        loading.visibility = View.INVISIBLE
    }
}
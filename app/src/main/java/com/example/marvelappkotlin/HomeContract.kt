package com.example.marvelappkotlin

import com.example.marvelappkotlin.model.Character
import com.example.marvelappkotlin.ui.HomeActivity

interface HomeContract {

    interface View {
        fun showLoading()
        fun initView()
        fun setupRecyclerView()
        fun onCardClicked(character: Character)
        fun showCharacter(listCharacter: ArrayList<Character>)
        fun hideLoading()
    }

    interface Presenter {
        fun create(activity: HomeActivity)
        fun subscribeToList(nextPage: Int)
        fun loadMore()
        fun sortByDate()
    }
}
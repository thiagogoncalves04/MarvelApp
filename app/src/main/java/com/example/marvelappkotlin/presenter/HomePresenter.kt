package com.example.marvelappkotlin.presenter

import android.util.Log
import com.example.marvelappkotlin.MarvelAPI
import com.example.marvelappkotlin.model.Character
import com.example.marvelappkotlin.view.HomeActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter {
    lateinit var activity: HomeActivity
    var characterList : ArrayList<Character> = ArrayList()

    fun create(activity: HomeActivity){
        subscribeToList()
        this.activity = activity

    }

    private fun subscribeToList() {
        val disposable = MarvelAPI.getService().allCharacters()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { list ->
                    characterList.addAll(list.data.results)
                    activity.showCharacter(characterList)
                },
                { e ->
                    Log.e("NGVL", "Error", e)
                }
            )
    }
}
package com.example.marvelappkotlin.presenter

import android.util.Log
import com.example.marvelappkotlin.HomeContract
import com.example.marvelappkotlin.MarvelAPI
import com.example.marvelappkotlin.model.Character
import com.example.marvelappkotlin.ui.HomeActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter : HomeContract.Presenter {

    lateinit var activity: HomeActivity
    var characterList: ArrayList<Character> = ArrayList()

    override fun create(activity: HomeActivity) {
        subscribeToList()
        this.activity = activity
    }

    override fun subscribeToList() {
        val observable = MarvelAPI.getService().allCharacters()
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
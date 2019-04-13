package com.example.marvelappkotlin

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

class ScrollListener(val layoutManeger: StaggeredGridLayoutManager, val loadMoreCallBack: LoadMore): RecyclerView.OnScrollListener() {
    var threshold = 5

    init {
        threshold *= layoutManeger.spanCount
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val lastVisibleItem: Int = 0
        val totalItemCount: Int = layoutManeger.itemCount
        val listVisibleItens = layoutManeger.findLastVisibleItemPositions(null)
        if(getLastVisibleItem(listVisibleItens)+threshold >=totalItemCount){
            loadMoreCallBack.onLoadMore()
        }
    }

    fun getLastVisibleItem(listVisibleItens: IntArray): Int{
        return listVisibleItens.max() ?:0
    }
}
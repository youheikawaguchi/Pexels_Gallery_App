package com.android.y_kawaguchi.pexels_gallery_app.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScroll : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var loading = true

    companion object {
        var loadingLimitation = false
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager as? GridLayoutManager ?: return

        val visibleItemCount = recyclerView.childCount //RecyclerViewに表示されているアイテム数
        val totalItemCount = layoutManager.itemCount //アイテムの合計
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && !loadingLimitation && totalItemCount == (visibleItemCount + firstVisibleItem)) {
            onLoadMore()
        }
    }

    abstract fun onLoadMore()
}
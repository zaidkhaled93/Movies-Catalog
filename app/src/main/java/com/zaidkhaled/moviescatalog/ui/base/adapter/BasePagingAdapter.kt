package com.zaidkhaled.moviescatalog.ui.base.adapter

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


//Paging 3 base class, alternative paging solution.
abstract class BasePagingAdapter<M : Any, VH : RecyclerView.ViewHolder>(
    itemDiff: DiffUtil.ItemCallback<M>
) :
    PagingDataAdapter<M, VH>(
        itemDiff
    ) {

    var items: MutableList<M> = mutableListOf()

    var itemClickListener: OnItemClickListener<M>? = null

    fun submitItems(items: MutableList<M>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    fun get(position: Int): M? {
        return items[position]
    }
}
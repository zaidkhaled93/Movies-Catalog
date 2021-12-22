package com.zaidkhaled.moviescatalog.ui.base.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<M, VH : RecyclerView.ViewHolder>() :
    RecyclerView.Adapter<VH>() {
    var items: MutableList<M> = mutableListOf()


    var itemClickListener: OnItemClickListener<M>? = null

    fun submitItems(items: MutableList<M>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<M>) {
        this.itemClickListener = onItemClickListener
    }

    override fun getItemCount() = items.size

    fun get(position: Int): M? {
        return items[position]
    }

}

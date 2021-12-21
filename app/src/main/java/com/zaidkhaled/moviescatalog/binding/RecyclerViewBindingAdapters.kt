package com.zaidkhaled.moviescatalog.binding

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.zaidkhaled.moviescatalog.ui.base.adapter.BaseAdapter

@BindingAdapter("rvItems")
fun <MODEL> RecyclerView?.setItems(items: MutableList<MODEL>?) {
    if (this?.adapter is BaseAdapter<*, *> && adapter != null && items != null) {
        (adapter as BaseAdapter<MODEL, *>).submitItems(items)
    }
}

@BindingAdapter("rvItems")
fun <MODEL> RecyclerView?.setItems(items: LiveData<MutableList<MODEL>>?) {
    if (this?.adapter is BaseAdapter<*, *> && adapter != null && items != null && items.value != null) {
        (adapter as BaseAdapter<MODEL, *>).submitItems(items.value ?: arrayListOf())
    }
}
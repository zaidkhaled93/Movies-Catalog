package com.zaidkhaled.moviescatalog.ui.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<M>(
    view: View
) : RecyclerView.ViewHolder(view) {

    abstract fun bindItem(item: M?, position: Int)

}
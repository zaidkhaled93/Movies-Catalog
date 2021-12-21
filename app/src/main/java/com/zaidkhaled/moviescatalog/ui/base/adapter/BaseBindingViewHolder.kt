package com.zaidkhaled.moviescatalog.ui.base.adapter

import androidx.databinding.ViewDataBinding

abstract class BaseBindingViewHolder<M>(
    val viewDataBinding: ViewDataBinding
) : BaseViewHolder<M>(viewDataBinding.root) {

    @Suppress("UNCHECKED_CAST")
    inline fun <T : ViewDataBinding> bind(binding: T.() -> Unit) {
        binding(viewDataBinding as T)
        viewDataBinding.executePendingBindings()
    }
}
package com.zaidkhaled.moviescatalog.ui.movies.moviesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zaidkhaled.moviescatalog.R
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import com.zaidkhaled.moviescatalog.databinding.RowMovieBinding
import com.zaidkhaled.moviescatalog.ui.base.adapter.BaseAdapter
import javax.inject.Inject

class MoviesListAdapter @Inject constructor() :
    BaseAdapter<MovieResponse?, MoviesListAdapter.ViewHolder>() {

    class ViewHolder(val view: RowMovieBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<RowMovieBinding>(
            inflater,
            R.layout.row_movie, parent, false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.movie = items[position]

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClicked(it, it, get(position), position)
        }
    }

}
package com.zaidkhaled.moviescatalog.ui.movies.moviesList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.zaidkhaled.moviescatalog.R
import com.zaidkhaled.moviescatalog.databinding.FragmentMoviesListBinding
import com.zaidkhaled.moviescatalog.ui.base.fragments.BaseBindingFragment
import com.zaidkhaled.moviescatalog.ui.movies.viewModel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : BaseBindingFragment<FragmentMoviesListBinding>() {

    override val layoutId: Int =
        R.layout.fragment_movies_list

    private val viewModel: MoviesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
package com.zaidkhaled.moviescatalog.ui.movies.movieDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.zaidkhaled.moviescatalog.R
import com.zaidkhaled.moviescatalog.databinding.FragmentMovieDetailsBinding
import com.zaidkhaled.moviescatalog.ui.base.fragments.BaseBindingFragment
import com.zaidkhaled.moviescatalog.ui.movies.viewModel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseBindingFragment<FragmentMovieDetailsBinding>() {

    override val layoutId: Int =
        R.layout.fragment_movie_details

    private val viewModel: MoviesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
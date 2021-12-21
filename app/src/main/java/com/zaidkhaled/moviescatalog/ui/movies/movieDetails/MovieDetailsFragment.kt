package com.zaidkhaled.moviescatalog.ui.movies.movieDetails

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.zaidkhaled.moviescatalog.R
import com.zaidkhaled.moviescatalog.databinding.FragmentMovieDetailsBinding
import com.zaidkhaled.moviescatalog.ui.base.fragments.BaseBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseBindingFragment<FragmentMovieDetailsBinding>() {

    override val layoutId: Int =
        R.layout.fragment_movie_details

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //pass the movie passed object the data binding to handle UI rendering
        binding?.movie = args.passedMovie
    }

}
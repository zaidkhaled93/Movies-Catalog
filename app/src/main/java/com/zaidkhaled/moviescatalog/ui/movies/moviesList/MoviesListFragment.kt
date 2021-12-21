package com.zaidkhaled.moviescatalog.ui.movies.moviesList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.zaidkhaled.moviescatalog.R
import com.zaidkhaled.moviescatalog.common.enums.MovieSort
import com.zaidkhaled.moviescatalog.common.enums.Status
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import com.zaidkhaled.moviescatalog.data.models.wrappers.ListWrapper
import com.zaidkhaled.moviescatalog.data.models.wrappers.Resource
import com.zaidkhaled.moviescatalog.databinding.FragmentMoviesListBinding
import com.zaidkhaled.moviescatalog.extensions.hide
import com.zaidkhaled.moviescatalog.extensions.show
import com.zaidkhaled.moviescatalog.ui.base.adapter.OnItemClickListener
import com.zaidkhaled.moviescatalog.ui.base.fragments.BaseBindingFragment
import com.zaidkhaled.moviescatalog.ui.movies.viewModel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.popular_movies_section.*
import kotlinx.android.synthetic.main.revenue_movies_section.*
import kotlinx.android.synthetic.main.top_rated_movies_section.*
import javax.inject.Inject

@AndroidEntryPoint
class MoviesListFragment : BaseBindingFragment<FragmentMoviesListBinding>() {

    override val layoutId: Int =
        R.layout.fragment_movies_list

    private val viewModel: MoviesViewModel by activityViewModels()

    @Inject
    lateinit var popularMoviesAdapter: MoviesListAdapter

    @Inject
    lateinit var topRatedMoviesAdapter: MoviesListAdapter

    @Inject
    lateinit var revenueMoviesAdapter: MoviesListAdapter

    private var popularMoviesPage: Int = 1
    private var topRatedMoviesPage: Int = 1
    private var revenueMoviesPage: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        setAdapters()
        loadMovies()
    }

    private fun loadMovies() {
        //load popular movies
        viewModel.loadMoviesListApi(popularMoviesPage, MovieSort.Popularity)
            .observe(viewLifecycleOwner, moviesObserver)
        //load top rated movies
        viewModel.loadMoviesListApi(topRatedMoviesPage, MovieSort.TopRated)
            .observe(viewLifecycleOwner, moviesObserver)
        //load revenue movies
        viewModel.loadMoviesListApi(revenueMoviesPage, MovieSort.Revenue)
            .observe(viewLifecycleOwner, moviesObserver)
    }

    //observe emitted response status to handle loading and errors
    private val moviesObserver = Observer<Resource<ListWrapper<MovieResponse>>> {
        it?.let { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    binding?.progressBar.hide()
                }
                Status.ERROR -> {
                    binding?.progressBar.hide()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    binding?.progressBar.show()
                }
                Status.CUSTOM_ERROR -> {
                    binding?.progressBar.hide()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setAdapters() {
        //configure popular movies adapter
        popularMoviesAdapter.setOnItemClickListener(object : OnItemClickListener<MovieResponse?> {
            override fun onItemClicked(
                view: View?,
                parentView: View?,
                item: MovieResponse?,
                position: Int
            ) {
                handleMovieItemClick(item)
            }
        })
        rv_popular_movies.adapter = popularMoviesAdapter

        //configure top rated movies adapter
        topRatedMoviesAdapter.setOnItemClickListener(object : OnItemClickListener<MovieResponse?> {
            override fun onItemClicked(
                view: View?,
                parentView: View?,
                item: MovieResponse?,
                position: Int
            ) {
                handleMovieItemClick(item)
            }
        })
        rv_top_rated_movies.adapter = topRatedMoviesAdapter

        //configure revenue movies adapter
        revenueMoviesAdapter.setOnItemClickListener(object : OnItemClickListener<MovieResponse?> {
            override fun onItemClicked(
                view: View?,
                parentView: View?,
                item: MovieResponse?,
                position: Int
            ) {
                handleMovieItemClick(item)
            }
        })
        rv_revenue_movies.adapter = revenueMoviesAdapter
    }

    private fun handleMovieItemClick(movie: MovieResponse?) {
        //open movie details from clicked item
        findNavController().navigate(
            R.id.openMovieDetailsFragment,
            bundleOf("passedMovie" to movie)
        )
    }
}
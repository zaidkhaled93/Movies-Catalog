package com.zaidkhaled.moviescatalog.ui.movies.moviesList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.zaidkhaled.moviescatalog.R
import com.zaidkhaled.moviescatalog.common.enums.Status
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapters()
        loadMovies()
    }

    private fun loadMovies() {
        viewModel.loadMoviesListApi(1).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding?.progressBar.hide()
                        it.data?.results?.let { list ->
                            popularMoviesAdapter.submitItems(list.toMutableList())
                            topRatedMoviesAdapter.submitItems(list.toMutableList())
                            revenueMoviesAdapter.submitItems(list.toMutableList())
                        }
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
        })
    }

    private fun setAdapters() {
        popularMoviesAdapter.setOnItemClickListener(object : OnItemClickListener<MovieResponse?> {
            override fun onItemClicked(
                view: View?,
                parentView: View?,
                item: MovieResponse?,
                position: Int
            ) {
                //todo
            }
        })
        rv_popular_movies.adapter = popularMoviesAdapter


        topRatedMoviesAdapter.setOnItemClickListener(object : OnItemClickListener<MovieResponse?> {
            override fun onItemClicked(
                view: View?,
                parentView: View?,
                item: MovieResponse?,
                position: Int
            ) {
                //todo
            }
        })
        rv_top_rated_movies.adapter = topRatedMoviesAdapter


        revenueMoviesAdapter.setOnItemClickListener(object : OnItemClickListener<MovieResponse?> {
            override fun onItemClicked(
                view: View?,
                parentView: View?,
                item: MovieResponse?,
                position: Int
            ) {
                //todo
            }
        })
        rv_revenue_movies.adapter = revenueMoviesAdapter
    }
}
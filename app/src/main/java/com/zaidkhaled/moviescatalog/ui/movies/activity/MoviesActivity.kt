package com.zaidkhaled.moviescatalog.ui.movies.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.zaidkhaled.moviescatalog.R
import com.zaidkhaled.moviescatalog.databinding.ActivityMoviesBinding
import com.zaidkhaled.moviescatalog.ui.base.activities.BaseBindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movies.*

@AndroidEntryPoint
class MoviesActivity : BaseBindingActivity<ActivityMoviesBinding>() {

    override val layoutId: Int = R.layout.activity_movies

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpNavigationGraph()
    }

    //set up the container activity navigation graph
    private fun setUpNavigationGraph() {
        val navHostFragment = movies_nav_host_fragment as NavHostFragment
        navController = navHostFragment.navController
        val inflater = navController.navInflater
        val graph = inflater.inflate(R.navigation.movies_nav_graph)

        navHostFragment.navController.graph = graph
    }
}
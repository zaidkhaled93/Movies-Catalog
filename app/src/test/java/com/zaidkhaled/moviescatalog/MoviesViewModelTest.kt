package com.zaidkhaled.moviescatalog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.zaidkhaled.moviescatalog.data.daos.MoviesLocalDao
import com.zaidkhaled.moviescatalog.data.daos.MoviesRemoteDao
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import com.zaidkhaled.moviescatalog.data.repositories.moviesLocalRepository.MoviesLocalRepositoryImpl
import com.zaidkhaled.moviescatalog.data.repositories.moviesRepository.MoviesRepositoryImpl
import com.zaidkhaled.moviescatalog.ui.movies.viewModel.MoviesViewModel
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
@Config(sdk = [29])
class MoviesViewModelTest : TestCase() {

    @Inject
    lateinit var remoteDao: MoviesRemoteDao

    @Inject
    lateinit var localDao: MoviesLocalDao

    @Inject
    lateinit var viewModel: MoviesViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        val remoteRepository = MoviesRepositoryImpl(remoteDao)
        val localRepository = MoviesLocalRepositoryImpl(localDao)
        viewModel = MoviesViewModel(remoteRepository, localRepository)
    }

    @Test
    fun testAddingMovies() {
        viewModel.cacheMoviesList(
            arrayListOf(
                MovieResponse(1, "poster_path", "original_title", "dummy overview", 7.1, 1809),
                MovieResponse(2, "poster_path2", "original_title2", "dummy2 overview", 9.1, 119)
            )
        )
        viewModel.loadCachedMovies()
        val result = viewModel.popularMoviesList.value?.find {
            it.id == 2 && it.overview == "dummy2 overview"
        }
        assertThat(result != null).isTrue()
    }

}
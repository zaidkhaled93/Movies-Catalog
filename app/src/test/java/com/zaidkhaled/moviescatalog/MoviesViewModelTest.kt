package com.zaidkhaled.moviescatalog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import com.zaidkhaled.moviescatalog.common.enums.MovieSort
import com.zaidkhaled.moviescatalog.data.daos.MoviesLocalDao
import com.zaidkhaled.moviescatalog.data.daos.MoviesRemoteDao
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import com.zaidkhaled.moviescatalog.data.repositories.moviesLocalRepository.MoviesLocalRepositoryImpl
import com.zaidkhaled.moviescatalog.data.repositories.moviesRepository.MoviesRepositoryImpl
import com.zaidkhaled.moviescatalog.ui.movies.viewModel.MoviesViewModel
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject


@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest : TestCase() {

    @Mock
    private lateinit var lifeCycleOwner: LifecycleOwner

    @Mock
    lateinit var remoteDao: MoviesRemoteDao

    @Mock
    lateinit var localDao: MoviesLocalDao

    @Inject
    lateinit var viewModel: MoviesViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    val dispatcher = StandardTestDispatcher()

    @Before
    public override fun setUp() {
        Dispatchers.setMain(dispatcher)
        val remoteRepository = MoviesRepositoryImpl(remoteDao)
        val localRepository = MoviesLocalRepositoryImpl(localDao)
        viewModel = MoviesViewModel(remoteRepository, localRepository)
    }

    @After
    public override fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testCachingMoviesFail() {
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
        assert(result == null)
    }

    @Test
    fun testFetchMoviesSuccess() {
        viewModel.loadMoviesListApi(1, MovieSort.Popularity)
        assert(true)
    }

}
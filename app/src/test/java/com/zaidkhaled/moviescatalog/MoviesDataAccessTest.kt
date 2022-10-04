package com.zaidkhaled.moviescatalog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.zaidkhaled.moviescatalog.common.constants.NetworkConstants
import com.zaidkhaled.moviescatalog.common.enums.MovieSort
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import com.zaidkhaled.moviescatalog.data.models.wrappers.ListWrapper
import com.zaidkhaled.moviescatalog.data.repositories.moviesLocalRepository.MoviesLocalRepository
import com.zaidkhaled.moviescatalog.data.repositories.moviesRepository.MoviesRepository
import com.zaidkhaled.moviescatalog.extensions.getOrAwaitValue
import com.zaidkhaled.moviescatalog.ui.movies.viewModel.MoviesViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class MoviesDataAccessTest {

    @Mock
    lateinit var moviesRepo: MoviesRepository

    @Mock
    lateinit var moviesLocalRepo: MoviesLocalRepository

    @Inject
    lateinit var viewModel: MoviesViewModel

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<List<MovieResponse>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MoviesViewModel(moviesRepo, moviesLocalRepo)
        viewModel.popularMoviesList.observeForever(observer)
    }

    @Test
    fun `get cached movies`() = runBlocking {
        doReturn(listOf(getExpectedMovie())).`when`(moviesLocalRepo).getMovies()
        val result = moviesLocalRepo.getMovies()
        assert(result.isNotEmpty())
    }

    @Test
    fun `get remote movies`() = runBlocking {
        doReturn(getExpectedListWrapper()).`when`(moviesRepo).getMovies(NetworkConstants.API_KEY, MovieSort.Popularity.value, false, 1)
        val result = moviesRepo.getMovies(NetworkConstants.API_KEY, MovieSort.Popularity.value, false, 1).results
        assert(result.isNullOrEmpty().not())
    }

    @Test
    fun `observe popular movies`() = runBlocking {
        `when`(moviesRepo.getMovies(NetworkConstants.API_KEY, MovieSort.Popularity.value, false, 1)).thenReturn(getExpectedListWrapper())
        launch { viewModel.loadMoviesListApi(1, MovieSort.Popularity) }
        val popularMoviesValue = viewModel.popularMoviesList.getOrAwaitValue()
        assert(popularMoviesValue.isNullOrEmpty().not())
        verify(observer).onChanged(listOf(getExpectedMovie()))
    }

    private fun getExpectedListWrapper(): ListWrapper<MovieResponse> {
        val mockedData: MutableList<MovieResponse> = arrayListOf()
        mockedData.add(getExpectedMovie())
        return ListWrapper(mockedData, 1, 1, 1)
    }

    private fun getExpectedMovie() = MovieResponse(1, "poster_path", "original_title", "dummy overview", 7.1, 1809)
}
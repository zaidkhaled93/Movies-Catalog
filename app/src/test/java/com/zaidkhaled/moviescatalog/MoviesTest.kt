package com.zaidkhaled.moviescatalog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zaidkhaled.moviescatalog.common.constants.NetworkConstants
import com.zaidkhaled.moviescatalog.common.enums.MovieSort
import com.zaidkhaled.moviescatalog.data.daos.MoviesRemoteDao
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import com.zaidkhaled.moviescatalog.data.models.wrappers.ListWrapper
import com.zaidkhaled.moviescatalog.data.repositories.moviesLocalRepository.MoviesLocalRepository
import com.zaidkhaled.moviescatalog.data.repositories.moviesRepository.MoviesRepository
import com.zaidkhaled.moviescatalog.data.repositories.moviesRepository.MoviesRepositoryImpl
import com.zaidkhaled.moviescatalog.ui.movies.viewModel.MoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MoviesTest {

    @Mock
    lateinit var moviesRepo: MoviesRepository

    private lateinit var moviesRepoImpl: MoviesRepositoryImpl

    @Mock
    lateinit var moviesLocalRepo: MoviesLocalRepository

    @Mock
    lateinit var moviesDao: MoviesRemoteDao

    @Inject
    lateinit var viewModel: MoviesViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        moviesRepoImpl = MoviesRepositoryImpl(moviesDao)
        viewModel = MoviesViewModel(moviesRepo, moviesLocalRepo)
    }

    @Test
    fun `cache movies locally and get list with filter from local storage after`() = runTest {
        viewModel.cacheMoviesList(
            arrayListOf(
                MovieResponse(1, "poster_path", "original_title", "dummy overview", 7.1, 1809),
                MovieResponse(2, "poster_path2", "original_title2", "dummy2 overview", 9.1, 119)
            )
        )
        advanceUntilIdle()
        viewModel.loadCachedMovies()
        advanceUntilIdle()
        val result = viewModel.popularMoviesList.value?.find {
            it.id == 2 && it.overview == "dummy2 overview"
        }
        assert(result != null)
    }

    @Test
    fun `fetch popular movies list from remote Api success`() = runTest(UnconfinedTestDispatcher()) {
        doReturn(getMoviesMockedData()).`when`(moviesRepo).getMovies(NetworkConstants.API_KEY, MovieSort.Popularity.value, false, 1)
        val result = moviesRepo.getMovies(NetworkConstants.API_KEY, MovieSort.Popularity.value, false, 1).results
        assert(result.isNullOrEmpty().not())
    }

    private fun getMoviesMockedData(): ListWrapper<MovieResponse> {
        val mockedData: MutableList<MovieResponse> = arrayListOf()
        mockedData.add(MovieResponse(1, "poster_path", "original_title", "dummy overview", 7.1, 1809))
        return ListWrapper(mockedData, 1, 1, 1)
    }
}
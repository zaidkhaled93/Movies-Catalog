package com.zaidkhaled.moviescatalog.ui.movies.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.zaidkhaled.moviescatalog.common.constants.NetworkConstants
import com.zaidkhaled.moviescatalog.common.enums.MovieSort
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import com.zaidkhaled.moviescatalog.data.models.wrappers.Resource
import com.zaidkhaled.moviescatalog.data.repositories.moviesLocalRepository.MoviesLocalRepository
import com.zaidkhaled.moviescatalog.data.repositories.moviesRepository.MoviesRepository
import com.zaidkhaled.moviescatalog.ui.base.lifeCycle.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepo: MoviesRepository,
    private val localMoviesRepo: MoviesLocalRepository
) :
    BaseViewModel() {

    //the three movies lists liveData, that are connected to the UI using data binding
    val popularMoviesList: MutableLiveData<List<MovieResponse>> by lazy { MutableLiveData<List<MovieResponse>>() }
    val topRatedMoviesList: MutableLiveData<List<MovieResponse>> by lazy { MutableLiveData<List<MovieResponse>>() }
    val revenueMoviesList: MutableLiveData<List<MovieResponse>> by lazy { MutableLiveData<List<MovieResponse>>() }

    //api remote call to get movies list with pagination and sorting type
    fun loadMoviesListApi(page: Int, sortBy: MovieSort) = liveData {
        loadCachedMovies()
        emit(Resource.loading(data = null))
        try {
            val response =
                moviesRepo.getMovies(NetworkConstants.API_KEY, sortBy.value, false, page)
            if (response.results != null) {
                distributeMoviesListBySortType(response.results, sortBy)
                cacheMoviesList(response.results)
                emit(Resource.success(data = response))
            } else {
                //we can handle errors here depending on server response, for example 400, 401, 403...etc
                emit(
                    Resource.customError(
                        data = null,
                        message = "No results found!",
                        code = 400
                    )
                )
            }
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    //here we cache the movies locally
    private suspend fun cacheMoviesList(list: List<MovieResponse>) {
        localMoviesRepo.saveMovies(list)
    }

    /*here we load the local cached movies list until api retrieves new data.
    the local caching should include the movies by sort, but only to show room implementation
    , currently we are caching all movies locally without sorting.
    */
    private suspend fun loadCachedMovies() {
        val moviesList = localMoviesRepo.getMovies()
        popularMoviesList.postValue(moviesList)
        topRatedMoviesList.postValue(moviesList)
        revenueMoviesList.postValue(moviesList)
    }

    //set movies list by sorting type to be reflected in UI by data binding
    private fun distributeMoviesListBySortType(moviesList: List<MovieResponse>, sortBy: MovieSort) {
        when (sortBy) {
            MovieSort.Popularity -> popularMoviesList.postValue(moviesList)
            MovieSort.TopRated -> topRatedMoviesList.postValue(moviesList)
            MovieSort.Revenue -> revenueMoviesList.postValue(moviesList)
        }
    }
}
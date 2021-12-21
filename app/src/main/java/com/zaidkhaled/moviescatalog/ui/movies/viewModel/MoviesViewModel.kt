package com.zaidkhaled.moviescatalog.ui.movies.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import com.zaidkhaled.moviescatalog.data.models.wrappers.Resource
import com.zaidkhaled.moviescatalog.data.repositories.moviesRepository.MoviesRepository
import com.zaidkhaled.moviescatalog.ui.base.lifeCycle.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesRepo: MoviesRepository) :
    BaseViewModel() {

    private val moviesList: MutableLiveData<List<MovieResponse>> by lazy { MutableLiveData<List<MovieResponse>>() }

    fun loadMoviesListApi() = liveData {
        emit(Resource.loading(data = null))
        try {
            val response = moviesRepo.getMovies()
            if (response.success == true) {
//                val apiList = response.data ?: arrayListOf()
                emit(Resource.success(data = response))
            } else {
                emit(
                    Resource.customError(
                        data = null,
                        message = response.errorMessage ?: "Error Occurred!",
                        code = response.errorCode ?: 0
                    )
                )
            }
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
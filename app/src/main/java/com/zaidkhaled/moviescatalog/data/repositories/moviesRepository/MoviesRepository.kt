package com.zaidkhaled.moviescatalog.data.repositories.moviesRepository

import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import com.zaidkhaled.moviescatalog.data.models.wrappers.ListWrapper

interface MoviesRepository {
    suspend fun getMovies(): ListWrapper<MovieResponse>
}
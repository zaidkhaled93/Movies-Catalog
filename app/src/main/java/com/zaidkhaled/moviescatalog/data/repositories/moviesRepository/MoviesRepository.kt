package com.zaidkhaled.moviescatalog.data.repositories.moviesRepository

import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import com.zaidkhaled.moviescatalog.data.models.wrappers.ListWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesRepository {
    suspend fun getMovies(
        api_key: String,
        sort_by: String,
        include_video: Boolean,
        page: Int
    ): ListWrapper<MovieResponse>
}
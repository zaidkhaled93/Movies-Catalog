package com.zaidkhaled.moviescatalog.data.daos

import com.zaidkhaled.moviescatalog.data.models.wrappers.ListWrapper
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import retrofit2.http.GET

interface MoviesRemoteDao {

    @GET("movies")
    suspend fun getMovies(): ListWrapper<MovieResponse>

}
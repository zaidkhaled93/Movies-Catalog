package com.zaidkhaled.moviescatalog.data.daos

import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import com.zaidkhaled.moviescatalog.data.models.wrappers.ListWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesRemoteDao {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") api_key: String,
        @Query("sort_by") sort_by: String,
        @Query("include_video") include_video: Boolean,
        @Query("page") page: Int
    ): ListWrapper<MovieResponse>

}
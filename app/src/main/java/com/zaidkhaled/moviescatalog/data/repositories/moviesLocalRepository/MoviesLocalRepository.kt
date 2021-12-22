package com.zaidkhaled.moviescatalog.data.repositories.moviesLocalRepository

import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse

interface MoviesLocalRepository {

    suspend fun saveMovie(model: MovieResponse): Long

    suspend fun saveMovies(model: List<MovieResponse>)

    suspend fun getMovies(): List<MovieResponse>

    suspend fun deleteMovie(movieId: Long): Int

    suspend fun deleteAllMovies()
}
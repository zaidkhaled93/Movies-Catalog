package com.zaidkhaled.moviescatalog.data.repositories.moviesLocalRepository

import com.zaidkhaled.moviescatalog.data.daos.MoviesLocalDao
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import javax.inject.Inject

class MoviesLocalRepositoryImpl @Inject constructor(

    private val moviesLocalDao: MoviesLocalDao
) : MoviesLocalRepository {
    override suspend fun saveMovie(model: MovieResponse): Long {
        return moviesLocalDao.saveMovie(model)
    }

    override suspend fun saveMovies(model: List<MovieResponse>) {
        return moviesLocalDao.saveMovies(model)
    }

    override suspend fun getMovies(): List<MovieResponse> {
        return moviesLocalDao.getMovies()
    }

    override suspend fun deleteMovie(movieId: Long): Int {
        return moviesLocalDao.deleteMovie(movieId)
    }

    override suspend fun deleteAllMovies() {
        return moviesLocalDao.deleteAllMovies()
    }

}
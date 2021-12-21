package com.zaidkhaled.moviescatalog.data.repositories.moviesRepository

import com.zaidkhaled.moviescatalog.data.daos.MoviesRemoteDao
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import com.zaidkhaled.moviescatalog.data.models.wrappers.ListWrapper

class MoviesRepositoryImpl(private val remoteDao: MoviesRemoteDao) : MoviesRepository {
    override suspend fun getMovies(): ListWrapper<MovieResponse> {
        return remoteDao.getMovies()
    }
}
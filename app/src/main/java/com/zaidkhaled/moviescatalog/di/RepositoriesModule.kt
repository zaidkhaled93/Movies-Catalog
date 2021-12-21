package com.zaidkhaled.moviescatalog.di

import com.zaidkhaled.moviescatalog.data.daos.MoviesRemoteDao
import com.zaidkhaled.moviescatalog.data.repositories.moviesRepository.MoviesRepository
import com.zaidkhaled.moviescatalog.data.repositories.moviesRepository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    companion object {
        @Provides
        fun provideMoviesRepository(remoteDao: MoviesRemoteDao): MoviesRepository {
            return MoviesRepositoryImpl(
                remoteDao
            )
        }
    }
}
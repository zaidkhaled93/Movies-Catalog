package com.zaidkhaled.moviescatalog.di

import com.zaidkhaled.moviescatalog.data.daos.MoviesLocalDao
import com.zaidkhaled.moviescatalog.data.daos.MoviesRemoteDao
import com.zaidkhaled.moviescatalog.data.repositories.moviesLocalRepository.MoviesLocalRepository
import com.zaidkhaled.moviescatalog.data.repositories.moviesLocalRepository.MoviesLocalRepositoryImpl
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

        @Provides
        fun provideLocalMoviesRepository(localDao: MoviesLocalDao): MoviesLocalRepository {
            return MoviesLocalRepositoryImpl(
                localDao
            )
        }
    }
}
package com.zaidkhaled.moviescatalog.di

import com.zaidkhaled.moviescatalog.data.daos.MoviesRemoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class DaosModule {
    companion object {
        @Provides
        fun provideMoviesRemoteDao(retrofit: Retrofit): MoviesRemoteDao =
            retrofit.create(MoviesRemoteDao::class.java)
    }
}
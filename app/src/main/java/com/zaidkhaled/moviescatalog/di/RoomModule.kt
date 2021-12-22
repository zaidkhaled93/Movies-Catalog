package com.zaidkhaled.moviescatalog.di

import android.content.Context
import androidx.room.Room
import com.zaidkhaled.moviescatalog.data.db.ApplicationDB
import com.zaidkhaled.moviescatalog.data.db.ApplicationDB.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): ApplicationDB {
        return Room.databaseBuilder(
            context,
            ApplicationDB::class.java,
            DATABASE_NAME
        ).build()
    }
}
package com.zaidkhaled.moviescatalog.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zaidkhaled.moviescatalog.data.daos.MoviesLocalDao
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse

@Database(
    entities = [
        MovieResponse::class
    ], version = 1
)

abstract class ApplicationDB : RoomDatabase() {

    abstract fun movieLocalDao(): MoviesLocalDao

    companion object {
        const val DATABASE_NAME = "MoviesCatalog.db"
        const val TABLE_MOVIES = "MoviesTable"
    }

}
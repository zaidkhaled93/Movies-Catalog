package com.zaidkhaled.moviescatalog.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zaidkhaled.moviescatalog.data.db.ApplicationDB.Companion.TABLE_MOVIES
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse

@Dao
interface MoviesLocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(model: MovieResponse): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(model: List<MovieResponse>)

    @Query("SELECT * FROM $TABLE_MOVIES")
    suspend fun getMovies(): List<MovieResponse>

    @Query("DELETE FROM $TABLE_MOVIES WHERE id = :movieId")
    suspend fun deleteMovie(movieId: Long): Int

    @Query("DELETE FROM $TABLE_MOVIES")
    suspend fun deleteAllMovies()
}
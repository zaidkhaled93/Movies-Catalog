package com.zaidkhaled.moviescatalog.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zaidkhaled.moviescatalog.data.models.responses.MovieResponse
import java.lang.reflect.Type


class MovieConverter {
    @TypeConverter
    fun storedStringToMovies(value: String?): List<MovieResponse>? {
        val listType: Type = object : TypeToken<List<MovieResponse>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun moviesToStoredString(list: List<MovieResponse>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}
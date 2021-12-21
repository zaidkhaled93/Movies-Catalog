package com.zaidkhaled.moviescatalog.data.models.responses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.zaidkhaled.moviescatalog.data.db.ApplicationDB

@Entity(tableName = ApplicationDB.TABLE_MOVIES)
data class MovieResponse(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "poster_path")
    @field:SerializedName("poster_path")
    val poster_path: String?,

    @ColumnInfo(name = "original_title")
    @field:SerializedName("original_title")
    val original_title: String?,

    @ColumnInfo(name = "overview")
    @field:SerializedName("overview")
    val overview: String?,

    @ColumnInfo(name = "vote_average")
    @field:SerializedName("vote_average")
    val vote_average: Double?,

    @ColumnInfo(name = "vote_count")
    @field:SerializedName("vote_count")
    val vote_count: Int?
)
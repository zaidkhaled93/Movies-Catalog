package com.zaidkhaled.moviescatalog.data.models.wrappers

import com.squareup.moshi.Json

data class ListWrapper<M>(
    @Json(name = "data")
    val data: List<M>?,
    @Json(name = "totalRows")
    val totalRows: Int?,
    @Json(name = "success")
    val success: Boolean?,
    @Json(name = "errorCode")
    val errorCode: Int?,
    @Json(name = "errorMessage")
    val errorMessage: String?
)

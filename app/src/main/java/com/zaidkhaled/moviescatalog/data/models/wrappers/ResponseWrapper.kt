package com.zaidkhaled.moviescatalog.data.models.wrappers

import com.squareup.moshi.Json

data class ResponseWrapper<M>(
    @Json(name = "errorCode")
    val code: Int?,
    @Json(name = "success")
    val success: Boolean?,
    @Json(name = "errorMessage")
    val message: String?,
    @Json(name = "data")
    val data: M?
)
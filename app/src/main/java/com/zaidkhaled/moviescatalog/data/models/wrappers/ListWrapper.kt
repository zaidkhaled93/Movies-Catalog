package com.zaidkhaled.moviescatalog.data.models.wrappers

import com.squareup.moshi.Json

data class ListWrapper<M>(
    @Json(name = "results")
    val results: List<M>?,
    @Json(name = "total_results")
    val total_results: Int?,
    @Json(name = "total_pages")
    val total_pages: Int?,
    @Json(name = "page")
    val page: Int?
)

package com.zaidkhaled.moviescatalog.common.enums

enum class MovieSort(val value: String) {
    Popularity("popularity.desc"),
    TopRated("vote_average.desc"),
    Revenue("revenue.desc")
}
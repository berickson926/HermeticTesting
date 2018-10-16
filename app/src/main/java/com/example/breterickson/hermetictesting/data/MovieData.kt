package com.example.breterickson.hermetictesting.data

import com.google.gson.annotations.SerializedName

const val POSER_BASE_URL = "https://image.tmdb.org/t/p/"

data class MoviePage(val results: List<MovieData> = emptyList())

data class MovieData(
    val title: String = "",
    @SerializedName("poster_path")
    val posterPath: String = ""
)
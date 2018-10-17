package com.example.breterickson.hermetictesting.data

import com.google.gson.annotations.SerializedName
import java.util.*

const val POSER_BASE_URL = "https://image.tmdb.org/t/p/w500"

data class MoviePage(val results: List<MovieData> = emptyList())

data class MovieData(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    @SerializedName("poster_path")
    val posterPath: String = ""
)
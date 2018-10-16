package com.example.breterickson.hermetictesting.main

import com.example.breterickson.hermetictesting.data.MovieData

interface MainView {

    fun showResults(results: List<MovieData>)
}
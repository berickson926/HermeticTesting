package com.example.breterickson.hermetictesting.data.remote

import com.example.breterickson.hermetictesting.data.MoviePage
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface MovieDbApi {

    @GET("search/movie?language=en-US&page=1&include_adult=false")
    fun getMovie(@Query("query") query: String, @Query("api_key") apiKey: String): Observable<MoviePage>
}

/**
 * Wrapper for network impl. details.
 */
@Singleton
class MovieService @Inject constructor() {

    // Insert your API key here
    private val apiKey = ""
    private val movieDbApi: MovieDbApi by lazy { createMovieAPI() }

    fun getMovie(keyword: String): Observable<MoviePage> {
        return movieDbApi.getMovie(keyword, apiKey)
    }

    private fun createMovieAPI(): MovieDbApi {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofitBuilder.create(MovieDbApi::class.java)
    }
}
package com.example.breterickson.hermetictesting.data.remote

import com.example.breterickson.hermetictesting.data.MoviePage
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

interface MovieDbApi {

    @GET("search/movie?language=en-US&page=1&include_adult=false")
    fun getMovie(@Query("query") query: String, @Query("api_key") apiKey: String): Observable<MoviePage>
}

/**
 * Wrapper for network impl. details.
 */
@Singleton
open class MovieService @Inject constructor(private val movieDbApi: MovieDbApi) {

    // Insert your API key here
    private val apiKey = "aff026b46049fd801b249b0e8bae97e2"

    open fun getMovie(keyword: String): Observable<MoviePage> {
        return movieDbApi.getMovie(keyword, apiKey)
    }
}
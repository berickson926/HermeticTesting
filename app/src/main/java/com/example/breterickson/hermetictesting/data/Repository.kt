package com.example.breterickson.hermetictesting.data

import com.example.breterickson.hermetictesting.data.remote.MovieService
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val movieService: MovieService
) {

    private val cache = mutableMapOf<String, List<MovieData>>()

    fun getMovie(query: String): Observable<List<MovieData>> {
        if (cache.containsKey(query)) {
            return Observable.just(cache[query])
        }

        return movieService.getMovie(query)
            .map { page -> page.results }
            .doOnNext { movies -> cache[query] = movies }
    }
}
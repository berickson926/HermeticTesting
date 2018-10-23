package com.example.breterickson.hermetictesting.main

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.breterickson.hermetictesting.R
import com.example.breterickson.hermetictesting.data.MovieData
import com.example.breterickson.hermetictesting.data.MoviePage
import com.example.breterickson.hermetictesting.data.remote.MovieService
import com.example.breterickson.hermetictesting.getTestApplicationComponent
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class MainActivityHermeticTest {

    @get:Rule
    val testRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Inject
    lateinit var movieService: MovieService

    @Before
    fun setUp() {
        // Allows Espresso to wait for async behaviors facilitated by RxJava2
        RxJavaPlugins.setInitComputationSchedulerHandler(Rx2Idler.create("Rx Computation Test Scheduler"))
        RxJavaPlugins.setInitIoSchedulerHandler(Rx2Idler.create("Rx IO Test Scheduler"))

        getTestApplicationComponent().inject(this)
    }

    @Test
    fun queryMovie() {
        // Given
        // We launch the Main page & have support for the query defined
        val query = "Robocop"
        val movieData = MovieData(
            title = query,
            posterPath = "/gtGreTdzYBuQsEwTliEFdTzPleV.jpg"
        )
        val page = MoviePage(listOf(movieData))
        `when`(movieService.getMovie(query))
            .thenReturn(Observable.just(page))

        testRule.launchActivity(Intent())

        // When
        // We enter the query & submit
        onView(withId(R.id.edit_query))
            .perform(replaceText(query))
        onView(withId(R.id.submit_button))
            .perform(click())

        // Then
        onView(withText(query))
            .check(matches(isCompletelyDisplayed()))
    }
}

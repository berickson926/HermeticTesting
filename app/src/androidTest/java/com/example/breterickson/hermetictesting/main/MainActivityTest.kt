package com.example.breterickson.hermetictesting.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.breterickson.hermetictesting.R
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val testRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        // Allows Espresso to wait for async behaviors facilitated by RxJava2
        RxJavaPlugins.setInitComputationSchedulerHandler(Rx2Idler.create("Rx Computation Test Scheduler"))
        RxJavaPlugins.setInitIoSchedulerHandler(Rx2Idler.create("Rx IO Test Scheduler"))
    }

    @Test
    fun queryMovie() {
        // Given
        // We are on the main page to query movies

        // When
        // We enter a query & submit
        val query = "Robocop"
        onView(withId(R.id.edit_query))
            .perform(replaceText(query))
        onView(withId(R.id.submit_button))
            .perform(click())

        // Then
        onView(withText(query))
            .check(matches(isCompletelyDisplayed()))
    }
}
package com.example.breterickson.hermetictesting.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.breterickson.hermetictesting.AndroidApplication
import com.example.breterickson.hermetictesting.R
import com.example.breterickson.hermetictesting.data.MovieData
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent.builder()
            .applicationComponent((application as AndroidApplication).component)
            .mainModule(MainModule(this))
            .build()
            .inject(this)

        submit_button.setOnClickListener {
            val query = edit_query.text.toString()
            presenter.submitQuery(query)
        }
    }

    override fun onPause() {
        super.onPause()
        // Be a good citizen and close any potential background tasks if we leave this screen.
        presenter.unsubscribe()
    }

    override fun showResults(results: List<MovieData>) {
        Log.d("VIEW", "Results: $results")
    }
}

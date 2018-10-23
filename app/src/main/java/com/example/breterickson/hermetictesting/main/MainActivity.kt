package com.example.breterickson.hermetictesting.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breterickson.hermetictesting.AndroidApplication
import com.example.breterickson.hermetictesting.R
import com.example.breterickson.hermetictesting.data.MovieData
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent.builder()
            .applicationComponent((application as AndroidApplication).getComponent())
            .mainModule(MainModule(this))
            .build()
            .inject(this)

        submit_button.setOnClickListener {
            val query = edit_query.text.toString()
            presenter.submitQuery(query)
        }

        result_list.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onPause() {
        super.onPause()
        // Be a good citizen and close any potential background tasks if we leave this screen.
        presenter.unsubscribe()
    }

    override fun showResults(results: List<MovieData>) {
        adapter.setResults(results)
    }
}

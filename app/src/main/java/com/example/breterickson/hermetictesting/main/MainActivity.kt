package com.example.breterickson.hermetictesting.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breterickson.hermetictesting.AndroidApplication
import com.example.breterickson.hermetictesting.R
import com.example.breterickson.hermetictesting.data.MovieData
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

fun Activity.hideKeyboard() {
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    val focus = currentFocus?.windowToken
    inputManager?.hideSoftInputFromWindow(focus, InputMethodManager.HIDE_NOT_ALWAYS)
}

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
            edit_query.text?.clear()
            hideKeyboard()
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

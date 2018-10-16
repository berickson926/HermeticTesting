package com.example.breterickson.hermetictesting.main

import android.util.Log
import com.example.breterickson.hermetictesting.PerActivity
import com.example.breterickson.hermetictesting.data.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerActivity
class MainPresenter @Inject constructor(
    private val view: MainView,
    private val repository: Repository,
    private val disposable: CompositeDisposable
) {

    fun submitQuery(query: String) {
        disposable.add(
            repository.getMovie(query)
                // Do task on background thread
                .subscribeOn(Schedulers.io())
                // Handle result on main thread to update UI
                .observeOn(AndroidSchedulers.mainThread())
                // Callbacks for a result or error
                .subscribe(view::showResults, this::showError)
        )
    }

    fun unsubscribe() {
        disposable.clear()
    }

    private fun showError(throwable: Throwable) {
        // Ideally have some actual error handling
        Log.e("Presenter", "Ooops!")
    }
}
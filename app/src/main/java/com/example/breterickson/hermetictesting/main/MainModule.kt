package com.example.breterickson.hermetictesting.main

import com.example.breterickson.hermetictesting.PerActivity
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class MainModule(private val activity: MainActivity) {

    @PerActivity
    @Provides
    fun providesView(): MainView {
        return activity
    }

    @PerActivity
    @Provides
    fun providesDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}
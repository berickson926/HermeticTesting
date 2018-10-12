package com.example.breterickson.hermetictesting.main

import dagger.Component

@Component()
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}
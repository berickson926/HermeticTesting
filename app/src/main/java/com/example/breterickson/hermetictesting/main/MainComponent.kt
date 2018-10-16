package com.example.breterickson.hermetictesting.main

import com.example.breterickson.hermetictesting.ApplicationComponent
import com.example.breterickson.hermetictesting.PerActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [MainModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}
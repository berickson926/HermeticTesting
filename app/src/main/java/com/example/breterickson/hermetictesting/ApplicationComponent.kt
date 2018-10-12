package com.example.breterickson.hermetictesting

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationComponent {

    fun inject(androidApplication: AndroidApplication)
}
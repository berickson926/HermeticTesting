package com.example.breterickson.hermetictesting

import com.example.breterickson.hermetictesting.data.Repository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component()
interface ApplicationComponent {

    fun inject(androidApplication: AndroidApplication)

    fun repository(): Repository
}
package com.example.breterickson.hermetictesting

import com.example.breterickson.hermetictesting.data.Repository
import com.example.breterickson.hermetictesting.data.remote.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(androidApplication: AndroidApplication)

    fun repository(): Repository
}
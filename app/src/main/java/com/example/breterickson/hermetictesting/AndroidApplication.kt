package com.example.breterickson.hermetictesting

import android.app.Application

class AndroidApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
            .build()
            .apply {
                component = this
                component.inject(this@AndroidApplication)
            }
    }
}
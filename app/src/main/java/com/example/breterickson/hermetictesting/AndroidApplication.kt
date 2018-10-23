package com.example.breterickson.hermetictesting

import android.app.Application

open class AndroidApplication : Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = prepareApplicationComponent()
        component.inject(this@AndroidApplication)
    }

    fun getComponent(): ApplicationComponent {
        return component
    }

    open fun prepareApplicationComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .build()
    }
}
package com.example.breterickson.hermetictesting

import android.app.Application
import android.app.Instrumentation
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class AndroidTestRunner : AndroidJUnitRunner() {

    override fun newApplication(classLoader: ClassLoader?, className: String?, context: Context?): Application {
        return Instrumentation.newApplication(AndroidTestApplication::class.java, context)
    }
}
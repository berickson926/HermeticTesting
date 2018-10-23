package com.example.breterickson.hermetictesting

class AndroidTestApplication : AndroidApplication() {

    override fun prepareApplicationComponent(): ApplicationComponent {
        return DaggerAndroidTestComponent.builder()
            .build()
    }
}
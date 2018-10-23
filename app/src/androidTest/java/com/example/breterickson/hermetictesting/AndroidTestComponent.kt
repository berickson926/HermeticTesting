package com.example.breterickson.hermetictesting

import androidx.test.InstrumentationRegistry
import com.example.breterickson.hermetictesting.data.remote.MovieService
import com.example.breterickson.hermetictesting.main.MainActivityHermeticTest
import dagger.Component
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import javax.inject.Singleton

/**
 * Helper for acquiring Test Object Graph for DI into test classes.
 */
fun getTestApplicationComponent(): AndroidTestComponent {
    val instrumentation = InstrumentationRegistry.getInstrumentation()
    val application = instrumentation.targetContext.applicationContext as AndroidTestApplication
    return application.getComponent() as AndroidTestComponent
}

@Singleton
@Component(modules = [MockRemoteDataSourceModule::class])
interface AndroidTestComponent : ApplicationComponent {

    // Define injection for android test classes here
    fun inject(mainActivityHermeticTest: MainActivityHermeticTest)
}

@Module
class MockRemoteDataSourceModule {

    @Singleton
    @Provides
    fun providesMovieService(): MovieService {
        return mock(MovieService::class.java)
    }

}
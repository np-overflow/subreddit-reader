package club.npoverflow.subredditreader

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun livedata_demo() {
        // Mock lifecycle owner
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        // Create MutableLiveData
        val x = MutableLiveData(5)
        x.observe({ lifecycle }) {
            println("The new value: $it")
        }

        println("Start")
        x.postValue(20)
        println("End")
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

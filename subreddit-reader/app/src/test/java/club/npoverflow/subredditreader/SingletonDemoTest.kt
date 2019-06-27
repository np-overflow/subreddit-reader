package club.npoverflow.subredditreader

import org.junit.Test

class MySingleton private constructor(
    private val name: String,
    private val aNumber: Int
) {
    companion object {
        private var INSTANCE: MySingleton? = null

        fun getInstance(name: String, aNumber: Int): MySingleton {
            if (INSTANCE == null) {
                INSTANCE = MySingleton(name, aNumber)
            }

            return INSTANCE!!
        }
    }

    fun showArgsAsString() = "$name, $aNumber"
}

class SingletonDemoTest {
    @Test
    fun singletonTest() {
        // Fails
        //val failInstance = MySingleton("something", 3)

        val instance1 = MySingleton.getInstance("Pizza", 1)
        val instance2 = MySingleton.getInstance("Orange", 2)
        val instance3 = MySingleton.getInstance("Apple", 3)
        val instance4 = MySingleton.getInstance("Noodles", 4)

        println("instance1: ${instance1.showArgsAsString()}")
        println("instance2: ${instance2.showArgsAsString()}")
        println("instance3: ${instance3.showArgsAsString()}")
        println("instance4: ${instance4.showArgsAsString()}")
    }
}
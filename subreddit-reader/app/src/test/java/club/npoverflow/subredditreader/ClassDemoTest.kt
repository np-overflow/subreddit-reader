package club.npoverflow.subredditreader

import org.junit.Test

class Dog(private val name: String) {
    fun bark() {
        println("bark bark I'm a dog")
    }

    fun sayName() {
        println("bark bark I'm $name")
    }
}

class ClassDemoTest {
    @Test
    fun instantiatingClass() {
        val dog1 = Dog("poofy")
        val dog2 = Dog("not poofy")

        dog1.bark()
        dog2.bark()
    }
}
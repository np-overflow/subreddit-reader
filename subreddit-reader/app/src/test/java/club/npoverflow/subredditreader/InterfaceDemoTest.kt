package club.npoverflow.subredditreader

import org.junit.Test

interface Caterer {
    fun cater()
}

class NeoGardenCaterer : Caterer {
    override fun cater() {
        println("Catered by NeoGarden")
    }
}

class DaPaoloCaterer : Caterer {
    override fun cater() {
        println("Catered by NeoGarden")
    }
}

class Wedding(private val caterer: Caterer)

class InterfaceDemoTest {
    @Test
    fun swapImplementations() {
        val caterer: Caterer = NeoGardenCaterer()

        val wedding = Wedding(caterer)
    }
}
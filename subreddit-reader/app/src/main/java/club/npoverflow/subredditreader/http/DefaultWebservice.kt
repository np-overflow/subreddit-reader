package club.npoverflow.subredditreader.http

import java.net.URL

class DefaultWebservice : Webservice {
    override fun get(url: String): String {
        return URL(url).readText()
    }

    override fun getByteArray(url: String): ByteArray {
        return URL(url).readBytes()
    }
}
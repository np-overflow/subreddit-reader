package club.npoverflow.subredditreader.http

interface Webservice {
    fun get(url: String): String

    fun getByteArray(url: String): ByteArray
}
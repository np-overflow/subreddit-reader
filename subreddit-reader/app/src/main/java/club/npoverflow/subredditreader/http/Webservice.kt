package club.npoverflow.subredditreader.http

interface Webservice {
    fun get(url: String): String
}
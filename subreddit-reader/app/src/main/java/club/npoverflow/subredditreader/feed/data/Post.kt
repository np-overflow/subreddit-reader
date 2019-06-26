package club.npoverflow.subredditreader.feed.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import club.npoverflow.subredditreader.http.Webservice

data class Post(
    val id: String,
    val author: String,
    val title: String,
    val content: String,
    val thumbnail: String,
    val url: String,
    val subreddit: String,
    var image: Bitmap? = null
) {
    fun fetchImage(webservice: Webservice): Bitmap? {
        if (image == null && (url.endsWith(".png") || url.endsWith(".jpg"))) {
            val data = webservice.getByteArray(url)
            return BitmapFactory.decodeByteArray(data, 0, data.size)
        }

        return null
    }
}

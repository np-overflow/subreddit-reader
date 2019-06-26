package club.npoverflow.subredditreader.feed.data.parser.orgjson

import club.npoverflow.subredditreader.feed.data.Post
import org.json.JSONObject
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

fun parsePostJson(jsonObject: JSONObject): Post {
    val data = jsonObject.getJSONObject("data")

    return Post(
        data.getString("id"),
        data.getString("author"),
        data.getString("title"),
        data.getString("selftext"),
        data.getString("thumbnail"),
        data.getString("url"),
        data.getString("subreddit").toLowerCase()
    )
}

fun orgjsonParse(Json: String): List<Post> {
    val jsonObject = JSONObject(Json)
    val children = jsonObject.getJSONObject("data").getJSONArray("children")

    return List(children.length()) {
        parsePostJson(children.getJSONObject(it))
    }
}
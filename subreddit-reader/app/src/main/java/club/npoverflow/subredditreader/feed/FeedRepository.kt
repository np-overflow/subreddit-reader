package club.npoverflow.subredditreader.feed

import android.content.Context
import androidx.room.RoomDatabase
import club.npoverflow.subredditreader.feed.data.Post
import club.npoverflow.subredditreader.feed.data.database.FeedDAL
import club.npoverflow.subredditreader.feed.data.database.room.FeedDatabase
import club.npoverflow.subredditreader.feed.data.database.room.RoomDAL
import club.npoverflow.subredditreader.feed.data.parser.parse
import club.npoverflow.subredditreader.http.DefaultWebservice
import club.npoverflow.subredditreader.http.Webservice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedRepository private constructor(
    private val feedDAL: FeedDAL,
    private val webservice: Webservice
) {
    // Repository should be a singleton
    companion object {
        var repository: FeedRepository? = null

        fun getInstance(context: Context): FeedRepository {
            if (repository == null) {
                // Just inject the arguments here
                val database = FeedDatabase.getInstance(context)
                val dal = RoomDAL(database)
                val webservice = DefaultWebservice()
                repository = FeedRepository(dal, webservice)
            }

            return repository!!
        }
    }

    private suspend fun fetchPostImages(posts: List<Post>): List<Post> = withContext(Dispatchers.IO) {
        List(posts.size) {
            val newPost = posts[it].copy()
            newPost.image = posts[it].fetchImage(webservice)
            newPost
        }
    }

    private suspend fun refreshDBFeed(subreddit: String) {
        // Always fetch from webservice. Assume that necessary checks have been done
        val data = webservice.get(subreddit)
        val newPosts = parse(data)
        fetchPostImages(newPosts)
        feedDAL.updateFeed(subreddit, newPosts)
    }

    suspend fun refreshFeed(subreddit: String): List<Post> {
        feedDAL.clearFeed(subreddit)
        refreshDBFeed(subreddit)

        return feedDAL.getPosts(subreddit)
    }

    suspend fun getFeed(subreddit: String): List<Post> {
        if (!feedDAL.hasFeed(subreddit)) {
            refreshDBFeed(subreddit)
        }

        return feedDAL.getPosts(subreddit)
    }
}
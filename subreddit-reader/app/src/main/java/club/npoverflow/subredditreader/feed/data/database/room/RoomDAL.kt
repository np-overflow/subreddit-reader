package club.npoverflow.subredditreader.feed.data.database.room

import club.npoverflow.subredditreader.feed.data.Post
import club.npoverflow.subredditreader.feed.data.database.FeedDAL

class RoomDAL(private val feedDatabase: FeedDatabase) : FeedDAL {
    override fun hasFeed(subreddit: String): Boolean {
        return feedDatabase.postDao().countPosts(subreddit) > 0
    }

    override fun getPosts(subreddit: String): List<Post> {
        val postEntities = feedDatabase.postDao().getPosts(subreddit)
        return List(postEntities.size) { postEntities[it].toPost() }
    }

    override fun createFeed(subreddit: String, posts: List<Post>) {
        val postEntities = List(posts.size) {
            PostEntity(posts[it])
        }
        feedDatabase.postDao().insertPosts(postEntities)
    }

    override fun updateFeed(subreddit: String, posts: List<Post>) {
        clearFeed(subreddit)
        createFeed(subreddit, posts)
    }

    override fun clearFeed(subreddit: String) {
        feedDatabase.postDao().deleteAllPosts(subreddit)
    }
}
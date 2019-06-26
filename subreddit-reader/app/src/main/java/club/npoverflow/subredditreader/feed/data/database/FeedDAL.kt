package club.npoverflow.subredditreader.feed.data.database

import club.npoverflow.subredditreader.feed.data.Post

interface FeedDAL {
    fun hasFeed(subreddit: String): Boolean

    fun getPosts(subreddit: String): List<Post>

    fun createFeed(subreddit: String, posts: List<Post>)

    fun updateFeed(subreddit: String, posts: List<Post>)

    fun clearFeed(subreddit: String)
}
package club.npoverflow.subredditreader.feed.data.database.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import club.npoverflow.subredditreader.feed.data.Post

@Entity
class PostEntity(
    @PrimaryKey val id: String,
    val author: String,
    val title: String,
    val content: String,
    val thumbnail: String,
    val url: String,
    val subreddit: String
) {
    constructor(post: Post): this(
        post.id,
        post.author,
        post.title,
        post.content,
        post.thumbnail,
        post.url,
        post.subreddit
    )

    fun toPost() = Post(
        id,
        author,
        title,
        content,
        thumbnail,
        url,
        subreddit
    )
}

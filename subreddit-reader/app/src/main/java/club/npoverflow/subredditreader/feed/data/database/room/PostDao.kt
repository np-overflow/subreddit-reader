package club.npoverflow.subredditreader.feed.data.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import club.npoverflow.subredditreader.feed.data.Post

@Dao
interface PostDao {
    @Insert(onConflict = REPLACE)
    fun insertPost(post: PostEntity)

    @Insert(onConflict = REPLACE)
    fun insertPosts(posts: List<PostEntity>)

    @Query("SELECT COUNT(*) FROM postentity WHERE subreddit = :subreddit")
    fun countPosts(subreddit: String): Int

    @Query("DELETE FROM postentity WHERE subreddit = :subreddit")
    fun deleteAllPosts(subreddit: String)

    @Query("SELECT * FROM postentity WHERE subreddit = :subreddit")
    fun getPosts(subreddit: String): List<PostEntity>
}
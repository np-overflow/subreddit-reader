package club.npoverflow.subredditreader.feed.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostEntity::class], version = 1)
abstract class FeedDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}

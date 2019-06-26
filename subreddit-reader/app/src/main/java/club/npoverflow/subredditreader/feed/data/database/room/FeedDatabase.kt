package club.npoverflow.subredditreader.feed.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PostEntity::class], version = 1)
abstract class FeedDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

    // Room database should be a singleton
    companion object {
        private var database: FeedDatabase? = null

        fun getInstance(context: Context): FeedDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    FeedDatabase::class.java,
                    "feed-database"
                ).build()
            }

            return database!!
        }
    }
}

package uz.gita.newsproject.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.newsproject.data.models.responses.SourcesItem
import uz.gita.newsproject.data.room.daos.SourceDao
import javax.inject.Singleton

@Singleton
@Database(entities = [SourcesItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sourceDao() : SourceDao
    companion object {
        private lateinit var instance: AppDatabase

        fun init(context: Context) {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "news_database")
                    .build()
            }
        }

        fun getDatabase() = instance
    }
}
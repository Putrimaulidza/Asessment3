package org.d3if3117.rumuspersegi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersegiEntity::class], version = 1, exportSchema = false)
abstract class PersegiDb : RoomDatabase() {
    abstract val dao: PersegiDao
    companion object {
        @Volatile
        private var INSTANCE: PersegiDb? = null
        fun getInstance(context: Context): PersegiDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PersegiDb::class.java,
                        "persegi.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
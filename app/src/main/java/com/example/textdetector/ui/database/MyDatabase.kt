package com.example.textdetector.ui.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.textdetector.ui.database.Dao.TweetDao
import com.example.textdetector.ui.database.model.Tweet

@Database(entities = [Tweet::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun tweetDao(): TweetDao

    companion object {
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase? {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, "Tweet")
                        .allowMainThreadQueries().build()
            }
            return INSTANCE
        }

    }

}
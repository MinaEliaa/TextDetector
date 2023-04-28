package com.example.textdetector.ui.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.textdetector.ui.database.model.Tweet

@Dao
interface TweetDao {
    @Insert
    fun insertTweet(tweet:Tweet)
    @Query("SELECT * FROM Tweet")
    fun selectAllTweet():List<Tweet>
}
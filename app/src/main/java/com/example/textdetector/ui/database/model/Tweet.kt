package com.example.textdetector.ui.database.model

import android.animation.ObjectAnimator
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tweet(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo
    var percent: Int? = null,
    @ColumnInfo
    var tweet: String? = null,
    @ColumnInfo
    var tweetType: Int? = null,
):java.io.Serializable
package com.example.textdetector.ui.home.fragments.archive

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.textdetector.R
import com.example.textdetector.ui.database.MyDatabase
import com.example.textdetector.ui.database.model.Tweet
import com.google.android.material.progressindicator.CircularProgressIndicator

class ProgressAdapter(private var items: List<Tweet>?) : RecyclerView.Adapter<ProgressAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar: CircularProgressIndicator = itemView.findViewById(R.id.progress_bar)
        val numberTextView: TextView = itemView.findViewById(R.id.number_text_view)
        val text1TextView: TextView = itemView.findViewById(R.id.text1_text_view)
        val text2TextView: TextView = itemView.findViewById(R.id.text2_text_view)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
        return ViewHolder(view)
    }

    fun getTweets(list: List<Tweet>?) {
        items = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items?.get(position)
        holder.numberTextView.text = item?.percent.toString()+"%"
        holder.text2TextView.text = item?.tweet
        if (item?.tweetType == 0){
            //change the Tweet Type
            holder.text1TextView.text = "HATE TWEET"
            //change the Tweet Color
            holder.text2TextView.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.blueProgess))
            //change the Indicator color
            holder.progressBar.setIndicatorColor(ContextCompat.getColor(holder.itemView.context, R.color.blueProgess))
            val progressAnimator = ObjectAnimator.ofInt(holder.progressBar, "progress", 0, item?.percent ?: 0)
            progressAnimator.duration = 2000
            progressAnimator.start()
            //toast the tweet
            holder.text2TextView.setOnClickListener {
                val inflater = LayoutInflater.from(holder.itemView.context)
                val layout = inflater.inflate(R.layout.tweethate_toast, null)
                val textView = layout.findViewById<TextView>(R.id.success)
                textView.text = item?.tweet
                val toast = Toast.makeText(holder.itemView.context, "", Toast.LENGTH_SHORT)
                toast.view = layout
                toast.show()
            }


        }else if (item?.tweetType == 1){
            //change the Tweet Type
            holder.text1TextView.text = "OFFENSIVE TWEET"
            //change the Tweet Color
            holder.text2TextView.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.redProgess))
            //change the Indicator color
            holder.progressBar.setIndicatorColor(ContextCompat.getColor(holder.itemView.context, R.color.redProgess))
            val progressAnimator = ObjectAnimator.ofInt(holder.progressBar, "progress", 0, item?.percent ?: 0)
            progressAnimator.duration = 2000
            progressAnimator.start()
            //toast the tweet
            holder.text2TextView.setOnClickListener {
                val inflater = LayoutInflater.from(holder.itemView.context)
                val layout = inflater.inflate(R.layout.tweetoffensive_toast, null)
                val textView = layout.findViewById<TextView>(R.id.success)
                textView.text = item?.tweet
                val toast = Toast.makeText(holder.itemView.context, "", Toast.LENGTH_SHORT)
                toast.view = layout
                toast.show()
            }

        }
        else{
            //change the Tweet Type
            holder.text1TextView.text = "NEITHER TWEET"
            //change the Tweet Color
            holder.text2TextView.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.yellowProgess))
            //change the Indicator color
            holder.progressBar.setIndicatorColor(ContextCompat.getColor(holder.itemView.context, R.color.yellowProgess))
            val progressAnimator = ObjectAnimator.ofInt(holder.progressBar, "progress", 0, item?.percent ?: 0)
            progressAnimator.duration = 2000
            progressAnimator.start()
            //toast the tweet
            holder.text2TextView.setOnClickListener {
                val inflater = LayoutInflater.from(holder.itemView.context)
                val layout = inflater.inflate(R.layout.tweetneither_toast, null)
                val textView = layout.findViewById<TextView>(R.id.success)
                textView.text = item?.tweet
                val toast = Toast.makeText(holder.itemView.context, "", Toast.LENGTH_SHORT)
                toast.view = layout
                toast.show()
            }

        }


        // Delete tweet from database and remove from list
        holder.deleteButton.setOnClickListener {
            val tweet = items?.get(holder.adapterPosition)
            if (tweet != null) {
                MyDatabase.getInstance(holder.itemView.context)?.tweetDao()?.deleteTweet(tweet)
                items = items?.minus(tweet)
                notifyItemRemoved(holder.adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

}
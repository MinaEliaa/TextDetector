package com.example.textdetector.ui.home.fragments.archive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.textdetector.R
import com.google.android.material.progressindicator.CircularProgressIndicator

class ProgressAdapter(private val items: List<ProgressItem>) : RecyclerView.Adapter<ProgressAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar: CircularProgressIndicator = itemView.findViewById(R.id.progress_bar)
        val numberTextView: TextView = itemView.findViewById(R.id.number_text_view)
        val text1TextView: TextView = itemView.findViewById(R.id.text1_text_view)
        val text2TextView: TextView = itemView.findViewById(R.id.text2_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.numberTextView.text = item.number
        holder.text1TextView.text = item.text1
        holder.text2TextView.text = item.text2
        // You can set the progress of the CircularProgressIndicator here as well if needed.
    }

    override fun getItemCount() = items.size
}
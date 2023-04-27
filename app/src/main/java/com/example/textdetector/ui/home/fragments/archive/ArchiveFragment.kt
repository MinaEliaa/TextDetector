package com.example.textdetector.ui.home.fragments.archive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.example.textdetector.R


class ArchiveFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_archive, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ProgressAdapter(getProgressItems()) // Replace getProgressItems() with your own function to get the list of ProgressItems.
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    private fun getProgressItems(): List<ProgressItem> {

        // Replace this with your own function to get the list of ProgressItems.
        return listOf(
            ProgressItem("62%", "Hate Tweet", "Hi , I am learning NLP"),
            ProgressItem("75%", "Offensive Tweet", "Your message has a toxicity score of 75."),
            ProgressItem("100%", "Neither Tweet", "Your message is positive"),
            ProgressItem("62%", "Hate Tweet", "Hi , I am learning NLP"),
            ProgressItem("75%", "Offensive Tweet", "Your message has a toxicity score of 75."),
            ProgressItem("100%", "Neither Tweet", "Your message is positive"),
            ProgressItem("62%", "Hate Tweet", "Hi , I am learning NLP"),
            ProgressItem("75%", "Offensive Tweet", "Your message has a toxicity score of 75."),
            ProgressItem("100%", "Neither Tweet", "Your message is positive"),
            ProgressItem("62%", "Hate Tweet", "Hi , I am learning NLP"),
            ProgressItem("75%", "Offensive Tweet", "Your message has a toxicity score of 75."),
            ProgressItem("100%", "Neither Tweet", "Your message is positive")



        )
    }
}
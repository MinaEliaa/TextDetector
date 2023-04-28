package com.example.textdetector.ui.home.fragments.archive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.example.textdetector.R
import com.example.textdetector.ui.database.MyDatabase


class ArchiveFragment : Fragment() {

    lateinit var tweetAdapter: ProgressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_archive, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        tweetAdapter = ProgressAdapter(null)
        recyclerView.adapter = tweetAdapter // Replace getProgressItems() with your own function to get the list of ProgressItems.
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onResume() {
        super.onResume()
        loadTweets()
    }

    private fun loadTweets() {
        val list = MyDatabase.getInstance(requireContext())?.tweetDao()?.selectAllTweet()
        tweetAdapter.getTweets(list)
    }


}
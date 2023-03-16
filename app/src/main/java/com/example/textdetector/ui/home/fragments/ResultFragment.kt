package com.example.textdetector.ui.home.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.textdetector.R
import com.google.android.material.progressindicator.CircularProgressIndicator


class ResultFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<CircularProgressIndicator>(R.id.progressbar1)
        val animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 62)
        animator.duration = 1000
        animator.start()

        val progressBar2 = view.findViewById<CircularProgressIndicator>(R.id.progressbar2)
        val animator2 = ObjectAnimator.ofInt(progressBar2, "progress", 0, 30)
        animator2.duration = 1000
        animator2.start()

        val progressBar3 = view.findViewById<CircularProgressIndicator>(R.id.progressbar3)
        val animator3 = ObjectAnimator.ofInt(progressBar3, "progress", 0, 8)
        animator3.duration = 1000
        animator3.start()


    }


}
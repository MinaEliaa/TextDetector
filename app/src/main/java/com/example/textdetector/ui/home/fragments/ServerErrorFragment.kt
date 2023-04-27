package com.example.textdetector.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.textdetector.R


class ServerErrorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_server_error, container, false)
        val tryAgainButton = view.findViewById<Button>(R.id.tryAgain_button)

        tryAgainButton.setOnClickListener {
            val addFragment = AddFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_contanier, addFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}




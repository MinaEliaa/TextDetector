package com.example.textdetector.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.textdetector.R



class AddFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)


        val showResultsButton = view.findViewById<Button>(R.id.showResults_button)
        showResultsButton.setOnClickListener {

            val resultSplashFragment = ResultSplashFragment()


            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_contanier, resultSplashFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }


}
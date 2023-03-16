package com.example.textdetector.ui.home.fragments


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.textdetector.R



class ResultSplashFragment : Fragment() {

    private val SPLASH_TIME_OUT = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_resultsplash, container, false)

        // Create a Handler to navigate to the ResultFragment after 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val resultFragment = ResultFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_contanier, resultFragment)
                .commit()

        }, SPLASH_TIME_OUT)

        return view
    }




}
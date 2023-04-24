package com.example.textdetector.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.textdetector.R
import com.example.textdetector.ui.home.fragments.ResultSplashFragment
import com.example.textdetector.ui.home.fragments.api.ApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AddFragment : Fragment() {

    private lateinit var arrow: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrow = view.findViewById(R.id.backarrow)

        arrow.setOnClickListener {
            val homeFragment = HomeFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_contanier, homeFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)


        val etTypeTweet = view.findViewById<EditText>(R.id.et_TypeTweet)
        val showResultsButton = view.findViewById<Button>(R.id.showResults_button)
        showResultsButton.setOnClickListener {
            val tweet = etTypeTweet.text.toString()
            val words = tweet.split(" ")
            if (words.size == 2) {
                val resultFragment = ResultFragment()
                val bundle = Bundle()
                bundle.putString("tweet", tweet)
                resultFragment.arguments = bundle
                val fragmentManager = requireActivity().supportFragmentManager
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_contanier, resultFragment)
                    .addToBackStack(null)
                    .commit()
            } else {
                val inflater = requireActivity().layoutInflater
                val layout = inflater.inflate(R.layout.add_tweet_unsuccessful_toast, null)
                val toast = Toast(requireContext())
                toast.duration = Toast.LENGTH_SHORT
                toast.view = layout
                toast.show()
//                Toast.makeText(requireContext(), "Tweet must have more than 2 words", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
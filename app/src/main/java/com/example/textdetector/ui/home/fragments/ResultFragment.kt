package com.example.textdetector.ui.home.fragments

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.textdetector.R
import com.example.textdetector.ui.home.fragments.api.ApiInterface
import com.example.textdetector.ui.home.fragments.api.PredictionRequest
import com.example.textdetector.ui.home.fragments.api.PredictionResponse
import com.google.android.material.progressindicator.CircularProgressIndicator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.ImageView as ImageView1

class ResultFragment : Fragment() {
    // Create an instance of the Retrofit class
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    // Create an instance of the API interface
    private val apiInterface = retrofit.create(ApiInterface::class.java)
    private lateinit var progressBar: ProgressBar



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
        progressBar.visibility = View.VISIBLE

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tweet = requireArguments().getString("tweet")
        Log.d("test tweet", "the Tweet: $tweet")

        progressBar = view.findViewById(R.id.progress_load)

        // Create a PredictionRequest object with the tweet text
        val requestModel = tweet?.let { PredictionRequest(it) }
        // Make a POST request to the /predict endpoint
        if (requestModel != null) {
            apiInterface.sendReq(requestModel).enqueue(object : Callback<PredictionResponse> {
                override fun onResponse(
                    call: Call<PredictionResponse>,
                    response: Response<PredictionResponse>
                ) {

                    progressBar.visibility = View.GONE

                    if (response.isSuccessful) {
                        val classLabel = response.body()?.classLabel
                        val offensiveProb = response.body()?.classProbs?.offensive
                        val hateProb = response.body()?.classProbs?.hate
                        val neitherProb = response.body()?.classProbs?.neither
                        val classProbList = listOf(
                            "Offensive" to offensiveProb,
                            "Hate" to hateProb,
                            "Neither" to neitherProb
                        )
                        val sortedClassProbList = classProbList.sortedByDescending {
                            it.second?.removeSuffix("%")?.toDoubleOrNull() ?: 0.0
                        }
                        Log.d("Prediction", "Class label: $classLabel")
                        sortedClassProbList.forEach { (label, prob) ->
                            Log.d("Prediction", "$label probability: ${prob ?: "null"}%")
                        }
                        val classLabel1 = sortedClassProbList[0].first
                        val classProb1 = sortedClassProbList[0].second
                        val classLabel2 = sortedClassProbList[1].first
                        val classProb2 = sortedClassProbList[1].second
                        val classLabel3 = sortedClassProbList[2].first
                        val classProb3 = sortedClassProbList[2].second

                        //update the labels and its percentages
                        val label_one = view.findViewById<TextView>(R.id.first_label)
                        label_one.text = "${classLabel1} language"
                        val prob_one = view.findViewById<TextView>(R.id.first_percentage)
                        prob_one.text = "${classProb1}"

                        val label_two = view.findViewById<TextView>(R.id.second_label)
                        label_two.text = "${classLabel2} language"
                        val prob_two = view.findViewById<TextView>(R.id.second_percentage)
                        prob_two.text = "${classProb2}"

                        val label_three = view.findViewById<TextView>(R.id.third_label)
                        label_three.text = "${classLabel3} language"
                        val prob_three = view.findViewById<TextView>(R.id.third_percentage)
                        prob_three.text = "${classProb3}"

                        updateLabelTweetText(classLabel)
                        updatePercentages(classProb1, classProb2, classProb3)
                        val max = sortedClassProbList[0].second ?: 0.0
                        val percentageTextView = view.findViewById<TextView>(R.id.percentage)
                        percentageTextView.text = "${max}"
                        /* Log.d("Prediction", "Class label: $classLabel1")
                         Log.d("Prediction", "Class prob: $classProb1")
                         Log.d("Prediction", "Class label: $classLabel2")
                         Log.d("Prediction", "Class prob: $classProb2")
                         Log.d("Prediction", "Class label: $classLabel3")
                         Log.d("Prediction", "Class prob: $classProb3")*/

                    } else {
                        // Handle the error
                        Log.e(
                            "PredictionError",
                            "Prediction request failed with status code ${response.code()}"
                        )}}
                override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                    // Handle the failure
                    progressBar.visibility = View.GONE
                    val servererror_fragment = ServerErrorFragment()
                    val bundle = Bundle()
                    servererror_fragment.arguments = bundle
                    val fragmentManager = requireActivity()
                        .supportFragmentManager
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_contanier, servererror_fragment)
                        .addToBackStack(null)
                        .commit()

                    Log.e("PredictionError", "Prediction request failed with exception", t)
                }
            })}}
    fun updatePercentages(classProb1: String?, classProb2: String?, classProb3: String?) {
        var x: Float? = null
        var y: Float? = null
        var z: Float? = null
        // Parse classProb1, classProb2, and classProb3 as floats, if possible
        try {
            x = classProb1?.replace("%", "")?.toFloat()
            y = classProb2?.replace("%", "")?.toFloat()
            z = classProb3?.replace("%", "")?.toFloat()
        } catch (e: NumberFormatException) {
            Log.e("MyApp", "Error parsing floats from strings: ${e.message}")
            return
        }
        // Get references to the progress bars and create animators for each one
        val progressBar1 = view?.findViewById<CircularProgressIndicator>(R.id.progressbar1)
        val animator1 =
            progressBar1?.let { ObjectAnimator.ofInt(it, "progress", 0, (x ?: 0f * 100).toInt()) }
        animator1?.duration = 1000
        val progressBar2 = view?.findViewById<CircularProgressIndicator>(R.id.progressbar2)
        val animator2 =
            progressBar2?.let { ObjectAnimator.ofInt(it, "progress", 0, (y ?: 0f * 100).toInt()) }
        animator2?.duration = 1000
        val progressBar3 = view?.findViewById<CircularProgressIndicator>(R.id.progressbar3)
        val animator3 =
            progressBar3?.let { ObjectAnimator.ofInt(it, "progress", 0, (z ?: 0f * 100).toInt()) }
        animator3?.duration = 1000
        // Start the animations
        animator1?.start()
        animator2?.start()
        animator3?.start()
    }
    private fun updateLabelTweetText(classLabel: Int?) {
        val labelTweet = requireView().findViewById<TextView>(R.id.tweet_type)
        when (classLabel) {
            0 -> labelTweet.text = "HATE TWEET"
            1 -> labelTweet.text = "OFFENSIVE TWEET"
            2 -> labelTweet.text = "NEITHER TWEET"
        }}}





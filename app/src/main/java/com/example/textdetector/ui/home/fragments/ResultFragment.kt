package com.example.textdetector.ui.home.fragments

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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


class ResultFragment : Fragment() {



    // Create an instance of the Retrofit class
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create an instance of the API interface
    private val apiInterface = retrofit.create(ApiInterface::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    @SuppressLint("ObjectAnimatorBinding")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tweet = requireArguments().getString("tweet")
        Log.d("antsh", "the Tweet: $tweet")
        // Create a PredictionRequest object with the tweet text

        val requestModel = PredictionRequest("son of bitch")

        // Make a POST request to the /predict endpoint
        apiInterface.sendReq(requestModel).enqueue(object : Callback<PredictionResponse> {
            override fun onResponse(
                call: Call<PredictionResponse>,
                response: Response<PredictionResponse>
            ) {
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

                    val sortedClassProbList = classProbList.sortedByDescending { it.second }
                    Log.d("Prediction", "Class label: $classLabel")
                    sortedClassProbList.forEach { (label, prob) ->
                        Log.d("Prediction", "$label probability: $prob")
                    }
                    val classLabel1 = sortedClassProbList[0].first
                    val classProb1 = sortedClassProbList[0].second
                    val classLabel2 = sortedClassProbList[1].first
                    val classProb2 = sortedClassProbList[1].second
                    val classLabel3 = sortedClassProbList[2].first
                    val classProb3 = sortedClassProbList[2].second

                    //update the labels and its percentages
                    val label_one = view.findViewById<TextView>(R.id.first_label)
                    label_one.text ="${classLabel1} language"
                    val prob_one = view.findViewById<TextView>(R.id.first_percentage)
                    prob_one.text ="${classProb1}"

                    val label_two = view.findViewById<TextView>(R.id.second_label)
                    label_two.text ="${classLabel2} language"
                    val prob_two = view.findViewById<TextView>(R.id.second_percentage)
                    prob_two.text ="${classProb2}"

                    val label_three = view.findViewById<TextView>(R.id.third_label)
                    label_three.text ="${classLabel3} language"
                    val prob_three = view.findViewById<TextView>(R.id.third_percentage)
                    prob_three.text ="${classProb3}"


                    Log.d("Prediction", "Class label: $classLabel1")
                    Log.d("Prediction", "Class prob: $classProb1")
                    Log.d("Prediction", "Class label: $classLabel2")
                    Log.d("Prediction", "Class prob: $classProb2")
                    Log.d("Prediction", "Class label: $classLabel3")
                    Log.d("Prediction", "Class prob: $classProb3")

                    updateLabelTweetText(classLabel)
                    updatePercentages(classProb1,classProb2,classProb3)
                    val max = sortedClassProbList[0].second ?: 0.0
                    val percentageTextView = view.findViewById<TextView>(R.id.percentage)
                    percentageTextView.text = "${max}"




                } else {
                    // Handle the error
                    Log.e(
                        "PredictionError",
                        "Prediction request failed with status code ${response.code()}"
                    )
                }
            }

            override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                // Handle the failure
                Log.e("PredictionError", "Prediction request failed with exception", t)
            }
        })



    }



    fun updatePercentages(classProb1: String?, classProb2: String?, classProb3: String?) {


        val progressBar = view?.findViewById<CircularProgressIndicator>(R.id.progressbar1)
        val animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 90)
        animator.duration = 1000
        animator.start()

        val progressBar2 = view?.findViewById<CircularProgressIndicator>(R.id.progressbar2)
        val animator2 = ObjectAnimator.ofInt(progressBar2, "progress", 0, 50)
        animator2.duration = 1000
        animator2.start()

        val progressBar3 = view?.findViewById<CircularProgressIndicator>(R.id.progressbar3)
        val animator3 = ObjectAnimator.ofInt(progressBar3, "progress", 0, 50)
        animator3.duration = 1000
        animator3.start()
    }

    /*private fun updatePercentages(classProb1: String?, classProb2: String?, classProb3: String?) {
        val progressBar1 = view?.findViewById<CircularProgressIndicator>(R.id.progressbar1)
        val progressPercentage1 = classProb1?.toFloatOrNull() ?: 0f // Desired percentage as float
        val progressValue1 = (progressPercentage1 / 100.0f) * progressBar1?.max!! // Calculate progress value as float
        val animator1 = ObjectAnimator.ofFloat(progressBar1, "progress", 0f, progressValue1)
        animator1.duration = 1000
        animator1.start()

        val progressBar2 = view?.findViewById<CircularProgressIndicator>(R.id.progressbar2)
        val progressPercentage2 = classProb2?.toFloatOrNull() ?: 0f // Desired percentage as float
        val progressValue2 = (progressPercentage2 / 100.0f) * progressBar2?.max!! // Calculate progress value as float
        val animator2 = ObjectAnimator.ofFloat(progressBar2, "progress", 0f, progressValue2)
        animator2.duration = 1000
        animator2.start()

        val progressBar3 = view?.findViewById<CircularProgressIndicator>(R.id.progressbar3)
        val progressPercentage3 = classProb3?.toFloatOrNull() ?: 0f // Desired percentage as float
        val progressValue3 = (progressPercentage3 / 100.0f) * progressBar3?.max!! // Calculate progress value as float
        val animator3 = ObjectAnimator.ofFloat(progressBar3, "progress", 0f, progressValue3)
        animator3.duration = 1000
        animator3.start()
    }*/


    private fun updateLabelTweetText(classLabel: Int?) {
        val labelTweet = requireView().findViewById<TextView>(R.id.tweet_type)

        when (classLabel) {
            0 -> labelTweet.text = "HATE TWEET"
            1 -> labelTweet.text = "OFFENSIVE TWEET"
            2 -> labelTweet.text = "NEITHER TWEET"
        }
    }


}





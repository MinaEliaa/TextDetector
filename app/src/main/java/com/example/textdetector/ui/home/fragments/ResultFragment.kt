package com.example.textdetector.ui.home.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        .baseUrl("http://127.0.0.1:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create an instance of the API interface
    private val apiInterface = retrofit.create(ApiInterface::class.java)



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

        // Create a PredictionRequest object with the tweet text
        val requestModel = PredictionRequest("I Love you")

        // Make a POST request to the /predict endpoint
        apiInterface.sendReq(requestModel).enqueue(object : Callback<PredictionResponse> {
            override fun onResponse(call: Call<PredictionResponse>, response: Response<PredictionResponse>) {
                if (response.isSuccessful) {
                    val classLabel = response.body()?.classLabel
                    val offensiveProb = response.body()?.classProbs?.offensive
                    val hateProb = response.body()?.classProbs?.hate
                    val neitherProb = response.body()?.classProbs?.neither

                    Log.d("Prediction", "Class label: $classLabel")
                    Log.d("Prediction", "Offensive probability: $offensiveProb")
                    Log.d("Prediction", "Hate probability: $hateProb")
                    Log.d("Prediction", "Neither probability: $neitherProb")
                    // Display the prediction results in the UI
//                    class_label_text_view.text = "Class label: $classLabel"
//                    offensive_prob_text_view.text = "Offensive probability: $offensiveProb"
//                    hate_prob_text_view.text = "Hate probability: $hateProb"
//                    neither_prob_text_view.text = "Neither probability: $neitherProb"
                } else {
                    // Handle the error
                    Log.e("PredictionError", "Prediction request failed with status code ${response.code()}")

                }
            }
            override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                // Handle the failure
                Log.e("PredictionError", "Prediction request failed with exception", t)

            }
        })


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
package com.example.textdetector.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.textdetector.R
import com.example.textdetector.ui.home.fragments.ResultSplashFragment
import com.example.textdetector.ui.home.fragments.api.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AddFragment : Fragment() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the Retrofit API service
        val retrofit = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)


            /*private val client = OkHttpClient.Builder().build()

            private val retrofit = Retrofit.Builder()
                .baseUrl("http://127.0.0.1:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            fun<T> buildService(service: Class<T>): T{
                return retrofit.create(service)
            }*/
        /*val response = ApiService.buildService(ApiInterface::class.java)
        response.sendReq(requestModel).enqueue(
            object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    Toast.makeText(this@MainActivity,response.message().toString(),Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity,t.toString(),Toast.LENGTH_LONG).show()
                }

            }
        )*/


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)

        val etTypeTweet = view.findViewById<EditText>(R.id.et_TypeTweet)
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
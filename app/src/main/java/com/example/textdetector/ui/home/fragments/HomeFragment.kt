package com.example.textdetector.ui.home.fragments



import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

import androidx.fragment.app.Fragment
import com.example.textdetector.R

class HomeFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val userNameEt :TextView
        userNameEt = requireActivity().findViewById(R.id.UserName_EditText)
        val userName  = requireActivity().intent.getStringExtra("userName")

        userNameEt.setText("$userName")*/

        }


    }




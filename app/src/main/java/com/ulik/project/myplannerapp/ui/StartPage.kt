package com.ulik.project.myplannerapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ulik.project.myplannerapp.R
import kotlinx.android.synthetic.main.fragment_start_page.*

class StartPage : Fragment(R.layout.fragment_start_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnStart.setOnClickListener{
            findNavController().navigate(R.id.action_startPage_to_addTaskFragment, null)
        }
    }
}
package com.ulik.project.myplannerapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ulik.project.myplannerapp.R
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_start_page.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.sharedViewModel

class StartPage : Fragment(R.layout.fragment_start_page) {

//    val tasksViewModel: TasksViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            withContext(Dispatchers.Main) {
                delay(700)
                findNavController().navigate(R.id.action_startPage_to_tasksFragment, null)
            }
        }

        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)?.visibility = View.INVISIBLE
    }

    override fun onPause() {
        super.onPause()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)?.visibility = View.VISIBLE
    }
}
package com.ulik.project.myplannerapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulik.project.myplannerapp.R
import com.ulik.project.myplannerapp.utilities.EventObserver
import org.koin.android.viewmodel.ext.android.sharedViewModel


class SharedTaskFragment : Fragment(R.layout.fragment_shared_task) {

    val tasksViewModel: TasksViewModel by sharedViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        tasksViewModel.getSharedTasks()
        tasksViewModel.showTaskSharedEvent.observe(viewLifecycleOwner, EventObserver {
            Log.d("Nurs","showTaskSharedEvent ${it}")
        })

    }
}
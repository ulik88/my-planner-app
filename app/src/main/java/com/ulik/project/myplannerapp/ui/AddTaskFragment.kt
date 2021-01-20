package com.ulik.project.myplannerapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_tasks.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AddTaskFragment : Fragment() {

    val tasksViewModel: TasksViewModel by sharedViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tasksViewModel.showErrorEvent.observe(viewLifecycleOwner, Observer {
            Log.d("Nuts", it)
        })
        tasksViewModel.loadingEvent.observe(viewLifecycleOwner, Observer {
            Log.d("Nuts", "show loading ${it}")
        })
        tasksViewModel.shoTaskSaveSuccesfuly.observe(viewLifecycleOwner, Observer {
            Log.d("Nuts", "task saved ${it}")
        })
        btnStart.setOnClickListener {
            var title = editTextTextPersonName.text.toString()
            var desc = editTextTextPersonName2.text.toString()
            tasksViewModel.saveTask(title, desc)
        }
    }
}
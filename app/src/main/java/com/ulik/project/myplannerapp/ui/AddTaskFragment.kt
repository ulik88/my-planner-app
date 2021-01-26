package com.ulik.project.myplannerapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ulik.project.myplannerapp.R
import com.ulik.project.myplannerapp.utilities.EventObserver
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.fragment_tasks.btnStart
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AddTaskFragment : Fragment(R.layout.fragment_add_task) {
    val tasksViewModel: TasksViewModel by sharedViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasksViewModel.showErrorEvent.observe(viewLifecycleOwner, EventObserver {
            Log.d("Nuts", it)
            Toast.makeText(requireContext(), "error ${it}", Toast.LENGTH_SHORT).show()
        })

        tasksViewModel.loadingEvent.observe(viewLifecycleOwner, EventObserver {
            progressBar.visibility = if (it) View.VISIBLE else View.INVISIBLE
            Log.d("Nuts", "show loading ${it}")
        })

        tasksViewModel.shoTaskSaveSuccesfuly.observe(viewLifecycleOwner, EventObserver {
            Log.d("Nuts", "task saved ${it}")
            Toast.makeText(requireContext(), "task saved successfuly", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addTaskFragment_to_tasksFragment)
        })

        btnStart.setOnClickListener {
            var title = editTextTextPersonName.text.toString()
            var desc = editTextTextPersonName2.text.toString()
            tasksViewModel.saveTask(title, desc)
        }
    }
}
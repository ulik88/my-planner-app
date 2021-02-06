package com.ulik.project.myplannerapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulik.project.myplannerapp.R
import com.ulik.project.myplannerapp.utilities.EventObserver
import com.ulik.project.myplannerapp.utilities.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.fragment_shared_task.*
import kotlinx.android.synthetic.main.fragment_tasks.*
import kotlinx.android.synthetic.main.fragment_tasks.recycler_view
import org.koin.android.viewmodel.ext.android.sharedViewModel


class SharedTaskFragment : Fragment(R.layout.fragment_shared_task) {

    lateinit var adapter: SharedTasksAdapter

    val tasksViewModel: TasksViewModel by sharedViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        tasksViewModel.getSharedTasks()
        tasksViewModel.showTaskSharedEvent.observe(viewLifecycleOwner, EventObserver {
            Log.d("Nurs","showTaskSharedEvent ${it}")
            adapter.update(it)
        })


        adapter = SharedTasksAdapter(tasksViewModel)
        with(recycler_view_shared) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())


        }
        recycler_view_shared.adapter = adapter

    }
}
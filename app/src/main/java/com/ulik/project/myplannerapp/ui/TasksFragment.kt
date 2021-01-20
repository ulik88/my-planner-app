package com.ulik.project.myplannerapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ulik.project.myplannerapp.R
import kotlinx.android.synthetic.main.fragment_tasks.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class TasksFragment : Fragment(R.layout.fragment_tasks) {
    val tasksViewModel: TasksViewModel by sharedViewModel()
    private lateinit var adapter: TasksAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_tasksFragment_to_addTaskFragment)
        }
        tasksViewModel.shoTaskSaveSuccesfuly.observe(viewLifecycleOwner, Observer {
            adapter.update(it.peekContent())
        })
    }
    private fun initRecyclerView() {
        adapter = TasksAdapter(tasksViewModel)
        with(recycler_view) {
            layoutManager = LinearLayoutManager(requireContext())
        }
        recycler_view.adapter = adapter
    }

}
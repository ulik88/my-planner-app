package com.ulik.project.myplannerapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulik.project.myplannerapp.R
import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.utilities.EventObserver
import com.ulik.project.myplannerapp.utilities.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.fragment_tasks.*
import kotlinx.android.synthetic.main.item_task.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class TasksFragment : Fragment(R.layout.fragment_tasks) {

    val tasksViewModel: TasksViewModel by sharedViewModel()


    lateinit var adapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedCallback()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()


        btn_save.setOnClickListener {
            findNavController().navigate(R.id.action_tasksFragment_to_addTaskFragment)
        }

        tasksViewModel.updateAdapter.observe(viewLifecycleOwner, EventObserver {
            adapter.update(it)
        })


        tasksViewModel.deleteTask.observe(viewLifecycleOwner, EventObserver{
            Toast.makeText(requireActivity(), "Deleted!", Toast.LENGTH_SHORT).show()
        })

        tasksViewModel.showLoadedTaskEvent.observe(viewLifecycleOwner,EventObserver{
            adapter.update(it)

        })

        tasksViewModel.openTaskDetails.observe(viewLifecycleOwner, EventObserver {task ->
            val directions = TasksFragmentDirections.actionTasksFragmentToTaskDetailFragment(task)

            findNavController().navigate(directions)
        })

//        tasksViewModel.showTaskSharedEvent.observe(viewLifecycleOwner, EventObserver{
//            val dirShare = TasksFragmentDirections.actionTasksFragmentToFavoriteFragment()
//            iv_isfavorite.setOnClickListener {
//                adapter.update(tasksViewModel.tasks)
//
//                findNavController().navigate(dirShare)
//            }
//        })
    }

}
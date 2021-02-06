package com.ulik.project.myplannerapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.ulik.project.myplannerapp.R
import com.ulik.project.myplannerapp.utilities.EventObserver
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    val tasksViewModel: TasksViewModel by sharedViewModel()

    lateinit var adapter: SharedFavoriteTasksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tasksViewModel: TasksViewModel by sharedViewModel()
//        val args: SharedTaskFragment? by navArgs()



        tasksViewModel.getFavoriteTask()
            tasksViewModel.showTaskSharedFavoriteEvent.observe(viewLifecycleOwner, EventObserver { task ->
                Log.d("Nurs","showTaskSharedEvent ${task}")

                adapter.update(task)

        })

        adapter = SharedFavoriteTasksAdapter(tasksViewModel)
        with(recycler_view_favorite) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())


        }
        recycler_view_favorite.adapter = adapter

    }
}
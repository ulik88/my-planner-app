package com.ulik.project.myplannerapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ulik.project.myplannerapp.R
import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.utilities.EventObserver
import kotlinx.android.synthetic.main.item_task.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tasksViewModel: TasksViewModel by sharedViewModel()
        val args: SharedTaskFragment? by navArgs()


//            tasksViewModel.getSharedFavoriteTasks()
            tasksViewModel.showTaskSharedFavoriteEvent.observe(viewLifecycleOwner, EventObserver { task ->
                Log.d("Nurs","showTaskSharedEvent ${task}")

        })
    }
}
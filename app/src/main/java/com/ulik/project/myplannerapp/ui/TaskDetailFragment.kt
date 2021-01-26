package com.ulik.project.myplannerapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ulik.project.myplannerapp.R
import com.ulik.project.myplannerapp.data.model.Task
import kotlinx.android.synthetic.main.fragment_task_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class TaskDetailFragment : Fragment(R.layout.fragment_task_detail) {

    val tasksViewModel: TasksViewModel by sharedViewModel()
    val args: TaskDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recievedTask = args.task
        tv_title_to_edit.text = recievedTask.title
        tv_description_to_edit.text = recievedTask.description

        edit_btn.setOnClickListener {

            var text = tv_title_to_edit.text.toString()
            var description = tv_description_to_edit.text.toString()
            var task = Task(title = text, description = description)

            val direction = TaskDetailFragmentDirections.actionTaskDetailFragmentToAddTaskFragment(recievedTask)
            findNavController().navigate(direction)
        }

    }
}
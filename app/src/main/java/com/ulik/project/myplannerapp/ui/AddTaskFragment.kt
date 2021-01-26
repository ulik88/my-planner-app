package com.ulik.project.myplannerapp.ui

import androidx.navigation.fragment.navArgs
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ulik.project.myplannerapp.R
import com.ulik.project.myplannerapp.utilities.EventObserver
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.fragment_tasks.btnSave
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AddTaskFragment : Fragment(R.layout.fragment_add_task) {
    val tasksViewModel: TasksViewModel by sharedViewModel()
    val args: AddTaskFragmentArgs? by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if( args?.task!=null){
            val recievedTask = args!!.task!!
            btnSave.text = "update task"
            editTextTextPersonName.setText(recievedTask.description)
            editTextTextPersonName2.setText(recievedTask.title)
            btnSave.setOnClickListener {
                var title = editTextTextPersonName.text.toString()
                var desc = editTextTextPersonName2.text.toString()

                tasksViewModel.taskUpdate(recievedTask.copy(title = title, description = desc))
            }
        }else{
            btnSave.text = "save task"
            btnSave.setOnClickListener {
                var title = editTextTextPersonName.text.toString()
                var desc = editTextTextPersonName2.text.toString()
                tasksViewModel.saveTask(title, desc)

            }
        }

        tasksViewModel.showErrorEvent.observe(viewLifecycleOwner, EventObserver {

            Toast.makeText(requireContext(), "error ${it}", Toast.LENGTH_SHORT).show()
        })

        tasksViewModel.loadingEvent.observe(viewLifecycleOwner, EventObserver {
            progressBar.visibility = if (it) View.VISIBLE else View.INVISIBLE

        })

        tasksViewModel.shoTaskSaveSuccesfuly.observe(viewLifecycleOwner, EventObserver {

            Log.d("Updated", "Deer" + it.toString())

            Toast.makeText(requireContext(), "task saved successfuly", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addTaskFragment_to_tasksFragment)
        })
    }
}
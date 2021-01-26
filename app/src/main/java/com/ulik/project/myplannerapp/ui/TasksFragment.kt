package com.ulik.project.myplannerapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

            val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
                    ItemTouchHelper.SimpleCallback(
                            0,
                            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
                    ) {
                override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                ): Boolean {
                    Toast.makeText(requireActivity(), "on Move", Toast.LENGTH_SHORT).show()
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                    Toast.makeText(requireActivity(), "on Swiped ", Toast.LENGTH_SHORT).show()
                    //Remove swiped item from list and notify the RecyclerView
                    val position = viewHolder.adapterPosition
                    tasksViewModel.remove(position)
                    adapter!!.notifyDataSetChanged()
                }
            }

            val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
            itemTouchHelper.attachToRecyclerView(recycler_view)

        }
        recycler_view.adapter = adapter
    }

}
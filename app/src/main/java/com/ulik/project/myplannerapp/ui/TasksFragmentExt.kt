package com.ulik.project.myplannerapp.ui

import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.RecyclerView
import com.ulik.project.myplannerapp.utilities.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.fragment_tasks.*

fun TasksFragment.initRecyclerView() {
    adapter = TasksAdapter(tasksViewModel)
    with(recycler_view) {
        layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recycler_view.adapter as TasksAdapter
                val pos = getPosition(viewHolder.adapterPosition)
                tasksViewModel.remove(tasksViewModel.tasks[pos])
                tasksViewModel.tasks.removeAt(pos)
                adapter.notifyDataSetChanged()
            }
        }

        val itemTouchHelper = androidx.recyclerview.widget.ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recycler_view)


    }
    recycler_view.adapter = adapter
}

fun TasksFragment.onBackPressedCallback(){
    val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
    requireActivity().onBackPressedDispatcher.addCallback(this, callback)

}


fun getPosition(adapterPosition: Int):Int{
    if (adapterPosition == -1 ) return 0
    return adapterPosition
}

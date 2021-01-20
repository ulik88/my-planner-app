package com.ulik.project.myplannerapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.FtsOptions
import com.ulik.project.myplannerapp.R
import com.ulik.project.myplannerapp.data.model.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TasksAdapter(val tasksViewModel: TasksViewModel) : RecyclerView.Adapter<TasksAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(tasksViewModel.tasks[position])
    }

    override fun getItemCount(): Int {
        return tasksViewModel.tasks.size
    }

    fun update(list: List<Task>) {
        tasksViewModel.tasks.clear()
        tasksViewModel.tasks.addAll(list)
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(task: Task) {
            with(itemView) {
                title_tv.text = task.title
                description.text = task.description
                complete_checkbox.isChecked = task.isCompleted
            }

//            if (task.isFavorite){
//                iv_isfavorite.setImageResource(R.drawable.ic_baseline_favorite_red)
//            }else{
//                iv_isfavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
//            }
//
//            iv_isfavorite.setOnClickListener {
//                tasksViewModel.taskEditUpdate(task.copy(isFavorite = !task.isFavorite))
//            }
        }
    }
}